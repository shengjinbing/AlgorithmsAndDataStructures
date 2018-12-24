package cn.modesty.suanfa.graph;

import java.util.Stack;

public class GraphMain {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
       /* graph.addEdge(0,5);
        graph.addEdge(4,3);
        graph.addEdge(0,1);
        graph.addEdge(9,12);
        graph.addEdge(6,4);
        graph.addEdge(5,4);
        graph.addEdge(0,2);
        graph.addEdge(11,12);
        graph.addEdge(9,10);
        graph.addEdge(0,6);
        graph.addEdge(7,8);
        graph.addEdge(9,11);
        graph.addEdge(5,3);*/
        graph.addEdge(0, 5);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(0, 1);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(0, 2);

        /*DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph, 0);
        depthFirstSearch.printEdge();*/
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, 0);
        breadthFirstPaths.printEdge();

        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.isHasCycle());

        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(graph, 0);
        Stack           stack           = (Stack<Integer>) depthFirstPaths.pathTo(5);
        while (!stack.isEmpty()) {
            Object o = stack.pop();
            System.out.print((Integer) o + ",");
        }

        Digraph       digraph       = new Digraph(6);
        digraph.addEdge(0,5);
        digraph.addEdge(5,4);
        digraph.addEdge(4,3);
        digraph.addEdge(3,5);

        DirectedCycle directedCycle = new DirectedCycle(digraph);
        Stack           stack1           = (Stack<Integer>)directedCycle.getCycle();
        while (!stack1.isEmpty()) {
            Object o = stack1.pop();
            System.out.print((Integer) o + ",");
        }

        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
        depthFirstOrder.Pre();
        depthFirstOrder.Post();
        depthFirstOrder.Reversepost();
    }



}
