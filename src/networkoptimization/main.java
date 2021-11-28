package networkoptimization;

import java.util.ArrayList;

class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphGenerator test1 = new GraphGenerator();
		
		Graph sparseGraph = test1.generateSparseGraph(5000, 6);
//		Graph denseGraph = test1.generateDenseGraph(5000, 20);
		
//		sparseGraph.printGraph();
		int result1 = 0;
		int result2 = 0;
		while(result1 == result2) {
//			System.out.println("=====================================");
			sparseGraph = test1.generateSparseGraph(20, 4);
			result1 = sparseGraph.findMBPath_Heap(8, 9);
			result2 = sparseGraph.findMBPath_Baisc(8, 9);
//			break;
		}
		
//		denseGraph.findMBPath_Heap(8, 9);
//		denseGraph.findMBPath_Baisc(8, 9);
		
	}

}
