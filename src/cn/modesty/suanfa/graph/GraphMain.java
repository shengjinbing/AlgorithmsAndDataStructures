package cn.modesty.suanfa.graph;

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
        graph.addEdge(0,5);
        graph.addEdge(2,4);
        graph.addEdge(2,3);
        graph.addEdge(1,2);
        graph.addEdge(0,1);
        graph.addEdge(3,4);
        graph.addEdge(3,5);
        graph.addEdge(0,2);

        /*DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph, 0);
        depthFirstSearch.printEdge();*/
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, 0);
        breadthFirstPaths.printEdge();

        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.isHasCycle());
    }
}
