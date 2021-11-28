package networkoptimization;

import java.util.ArrayList;

class main {

	static void test1() {
		GraphGenerator test1 = new GraphGenerator();
		
		Graph sparseGraph = test1.generateSparseGraph(100, 6);
		Graph denseGraph = test1.generateDenseGraph(100, 20);
		
//		sparseGraph.printGraph();
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int count = 0;
		while(result1 == result2 && result3 == result4 && count++ < 10000) {
//			System.out.println("=====================================");
			sparseGraph = test1.generateSparseGraph(100, 6);
			result1 = sparseGraph.findMBPath_Heap(8, 9);
			result2 = sparseGraph.findMBPath_Baisc(8, 9);
			sparseGraph = test1.generateDenseGraph(100, 20);
			result3 = denseGraph.findMBPath_Heap(8, 9);
			result4 = denseGraph.findMBPath_Baisc(8, 9);
//			break;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		
//		denseGraph.findMBPath_Heap(8, 9);
//		denseGraph.findMBPath_Baisc(8, 9);
		
	}

}
