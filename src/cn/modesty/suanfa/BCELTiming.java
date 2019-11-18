package cn.modesty.suanfa;

import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.classfile.ClassParser;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.generic.*;

import java.io.FileOutputStream;
import java.io.IOException;


public class BCELTiming {

    private static void addWrapper(ClassGen cgen, Method method) {
        /*第一部分只是初始化要使用的基本 BCEL 组件，它包括用要计时方法的信息初始化一个新的
        org.apache.bcel.generic.MethodGen 实例。我为这个 MethodGen 设置一个空的
        指令清单，在后面我将用实际的计时代码填充它。在第 2 部分，我用原来的方法创建第二个
        org.apache.bcel.generic.MethodGen 实例，然后从类中删除原来的方法。在第二个
        MethodGen 实例中，我只是让名字加上“$impl”后缀，然后调用 getMethod () 以将可修改的
        方法信息转换为固定形式的 org.apache.bcel.classfile.Method 实例。然后调用
        addMethod() 以便在类中添加改名后的方法。*/
        InstructionFactory ifact = new InstructionFactory(cgen);
        InstructionList ilist = new InstructionList();
        ConstantPoolGen pgen = cgen.getConstantPool();
        String cname = cgen.getClassName();
        //我为这个 MethodGen 设置一个空的指令清单，在后面我将用实际的计时代码填充它
        MethodGen wrapgen = new MethodGen(method, cname, pgen);
        wrapgen.setInstructionList(ilist);

        // 复制原有方法并且重命名
        MethodGen methgen = new MethodGen(method, cname, pgen);
        cgen.removeMethod(method);
        String iname = methgen.getName() + "$impl";
        methgen.setName(iname);
        cgen.addMethod(methgen.getMethod());
        Type result = methgen.getReturnType();


        // compute the size of the calling parameters
        Type[] types = methgen.getArgumentTypes();
        int slot = methgen.isStatic() ? 0 : 1;
        for (int i = 0; i < types.length; i++) {
            slot += types[i].getSize();
        }

        // save time prior to invocation
        ilist.append(ifact.createInvoke("java.lang.System",
                "currentTimeMillis", Type.LONG, Type.NO_ARGS,
                Constants.INVOKESTATIC));
        ilist.append(InstructionFactory.
                createStore(Type.LONG, slot));

        // call the wrapped method
        int offset = 0;
        short invoke = Constants.INVOKESTATIC;
        if (!methgen.isStatic()) {
            ilist.append(InstructionFactory.
                    createLoad(Type.OBJECT, 0));
            offset = 1;
            invoke = Constants.INVOKEVIRTUAL;
        }
        for (int i = 0; i < types.length; i++) {
            Type type = types[i];
            ilist.append(InstructionFactory.
                    createLoad(type, offset));
            offset += type.getSize();
        }
        ilist.append(ifact.createInvoke(cname,
                iname, result, types, invoke));

        // store result for return later
        if (result != Type.VOID) {
            ilist.append(InstructionFactory.
                    createStore(result, slot + 2));
        }

        // print time required for method call
        ilist.append(ifact.createFieldAccess("java.lang.System",
                "out", new ObjectType("java.io.PrintStream"),
                Constants.GETSTATIC));
        ilist.append(InstructionConstants.DUP);
        ilist.append(InstructionConstants.DUP);
        String text = "Call to method " + methgen.getName() +
                " took ";
        ilist.append(new PUSH(pgen, text));
        ilist.append(ifact.createInvoke("java.io.PrintStream",
                "print", Type.VOID, new Type[]{Type.STRING},
                Constants.INVOKEVIRTUAL));
        ilist.append(ifact.createInvoke("java.lang.System",
                "currentTimeMillis", Type.LONG, Type.NO_ARGS,
                Constants.INVOKESTATIC));
        ilist.append(InstructionFactory.
                createLoad(Type.LONG, slot));
        ilist.append(InstructionConstants.LSUB);
        ilist.append(ifact.createInvoke("java.io.PrintStream",
                "print", Type.VOID, new Type[]{Type.LONG},
                Constants.INVOKEVIRTUAL));
        ilist.append(new PUSH(pgen, " ms."));
        ilist.append(ifact.createInvoke("java.io.PrintStream",
                "println", Type.VOID, new Type[]{Type.STRING},
                Constants.INVOKEVIRTUAL));

        // return result from wrapped method call
        if (result != Type.VOID) {
            ilist.append(InstructionFactory.
                    createLoad(result, slot + 2));
        }
        ilist.append(InstructionFactory.createReturn(result));

        // finalize the constructed method
        //只是告诉 BCEL 不对构建的方法生成调试信息
        wrapgen.stripAttributes(true);
        //调用计算并设置方法的堆栈使用信息
        wrapgen.setMaxStack();
        wrapgen.setMaxLocals();
        cgen.addMethod(wrapgen.getMethod());
        ilist.dispose();
    }

    public static void main(String[] argv) {
        if (argv.length == 2 && argv[0].endsWith(".class")) {
            try {

                JavaClass jclas = new ClassParser(argv[0]).parse();
                ClassGen cgen = new ClassGen(jclas);
                Method[] methods = jclas.getMethods();
                int index;
                for (index = 0; index < methods.length; index++) {
                    if (methods[index].getName().equals(argv[1])) {
                        break;
                    }
                }
                if (index < methods.length) {
                    addWrapper(cgen, methods[index]);
                    FileOutputStream fos =
                            new FileOutputStream(argv[0]);
                    cgen.getJavaClass().dump(fos);
                    fos.close();
                } else {
                    System.err.println("Method " + argv[1] +
                            " not found in " + argv[0]);
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }

        } else {
            System.out.println
                    ("Usage: BCELTiming class-file method-name");
        }
    }




}
