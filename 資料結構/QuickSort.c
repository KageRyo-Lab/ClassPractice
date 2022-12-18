#include <stdio.h>
#include <stdlib.h>

// 定義快速排序函數
void quick_sort(int *data, int left, int right) {
	if (left >= right) return;   // 如果左端點索引大於或等於右端點索引，則退出函數
	int pivot = data[left]; 	 // 將陣列的第一個數字設為支點
	int i = left + 1, j = right; // 將左端點索引加 1，右端點索引不變
	while (i <= j) {
		while (i <= j && data[i] < pivot) i++;	// 如果陣列中第 i 個數字小於支點，則將 i 索引加 1
		while (i <= j && data[j] >= pivot) j--;	// 如果陣列中第 j 個數字大於等於支點，則將 j 索引減 1 
		if (i < j) {
		    	int temp = data[i];
		    	data[i] = data[j];
		    	data[j] = temp;
		    }
	}
	data[left] = data[j];
	data[j] = pivot;
	quick_sort(data, left, j - 1);
	quick_sort(data, j + 1, right);
}

// 主程式
int main() {
	printf("請輸入要排序的數字個數：\n");
	int num;
	scanf("%d", &num);
	
	printf("請輸入數字：\n");
	int *data = (int*)malloc(num * sizeof(int));
	for (int i = 0; i < num; i++) {
		printf("請輸入第 %d 個數字：", i + 1);
		scanf("%d", &data[i]);
	}
	
	printf("原始的數字：");
	for (int i = 0; i < num; i++) printf("%d ", data[i]);
	printf("\n");
	
	quick_sort(data, 0, num - 1);
	printf("排序後的數字：");
	for (int i = 0; i < num; i++) printf("%d ", data[i]);
	printf("\n");
	
	free(data);
	return 0;
}
