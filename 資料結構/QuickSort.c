#include <stdio.h>
#include <stdlib.h>
/*
思路： 
１. 如果左端點大於等於右端點，則退出函數。
２. 設定 pivot 為左端點的值，並設定 i 為左端點下一位，j 為右端點。
３. 循環直到 i 大於 j。
４. 如果 i 小於等於 j 且 data[i] 小於 pivot，則 i 加 1。
５. 如果 i 小於等於 j 且 data[j] 大於等於 pivot，則 j 減 1。
６. 如果 i 小於 j，則交換 data[i] 與 data[j] 的值。
７. 將 data[j] 設為左端點的值，並將 pivot 設為 data[j] 的值。
８. 對左半部分使用快速排序，對右半部分使用快速排序。
*/ 

// 定義快速排序函數
void quick_sort(int *data, int left, int right) {
	if (left >= right) return;		// 如果左端點大於等於右端點，則退出函數
	int pivot = data[left];			// 設定 pivot 為左端點的值
	int i = left + 1, j = right; 	// 設定 i 為左端點下一位，j 為右端點
	while (i <= j) {
		while (i <= j && data[i] < pivot) i++; 	// 如果 i 小於等於 j 且 data[i] 小於 pivot，則 i 加 1 
		while (i <= j && data[j] >= pivot) j--; // 如果 i 小於等於 j 且 data[j] 大於等於 pivot，則 j 減 1 
		if (i < j) {
		    int temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}
	}
	data[left] = data[j]; 			// 將 data[j] 設為左端點的值 
	data[j] = pivot;				// 將 pivot 設為 data[j] 的值
	quick_sort(data, left, j - 1);	// 對左半部分使用快速排序
	quick_sort(data, j + 1, right);	// 對右半部分使用快速排序
}

// 主程式
int main() {
	int n;
	// 提供使用者輸入並檢查是否於限定範圍內 
	do{
		printf("請輸入資料筆數 (n<=10): ");
		scanf("%d", &n);
		if(n > 10) printf("您輸入的為: %d, 請注意範圍: n<=10\n",n);
	}while(n > 10);
	 
	int originalNumbers[n];
	int sortedNumbers[n];
	int isSameOrder;
	// 提供使用者依序輸入資料 
	int *data = (int*)malloc(n * sizeof(int));
	for (int i = 0; i < n; i++) {
		printf("請輸入第 %d 筆資料: ", i + 1);
		scanf("%d", &data[i]);
	}
	// 顯示原始輸入的數字順序 
	printf("\n原始的數字: ");
	for (int i = 0; i < n; i++) {
		printf("%d ", data[i]);
		originalNumbers[i]=data[i];
	}
	printf("\n");
	// 顯示排序後的結果 
	quick_sort(data, 0, n - 1);
	printf("\n排序後的數字: ");
	for (int i = 0; i < n; i++) {
		printf("%d ", data[i]);
		sortedNumbers[i]=data[i];
		if (originalNumbers[i] == sortedNumbers[i]) isSameOrder=1;
		else isSameOrder=0;
	}	
	printf("\n");
	// 告知使用者原始數字與排序後的數字是否相符(即原輸入順序是否已為排序後結果) 
	if(isSameOrder==1) printf("原始數字與排序後的數字順序相同");
	else printf("原始數字與排序後的數字順序不同");
	
	free(data);
	return 0;
}

