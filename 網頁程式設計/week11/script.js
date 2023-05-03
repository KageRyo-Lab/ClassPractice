// 生成表格
function createTable(data) {
    const tableBody = document.getElementById("table-body");
    const headerRow = document.getElementById("header-row");

    // 標題
    Object.keys(data[0]).forEach(key => {
        const header = document.createElement("th");
        header.textContent = key;
        headerRow.appendChild(header);
    });

    // 內容
    data.forEach(item => {
        const row = document.createElement("tr");

        Object.values(item).forEach(value => {
            const cell = document.createElement("td");
            cell.textContent = value;
            row.appendChild(cell);
        });

        tableBody.appendChild(row);
    });
}

// 建立 XMLHttpRequest 物件
const xHttp = new XMLHttpRequest();
xHttp.onload = function () {
    const data = JSON.parse(this.responseText);
    createTable(data);
}

// 爬臺中市路口即時影像的open data
xHttp.open("GET", "https://datacenter.taichung.gov.tw/Swagger/OpenData/566b84f1-dd8f-4e28-8da7-64e98ece837b?limit=1000");
xHttp.send();