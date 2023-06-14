let arr = [];
let pg = localStorage.getItem("pg") ? parseInt(localStorage.getItem("pg")) : 1;
let num = localStorage.getItem("num") ? parseInt(localStorage.getItem("num")) : 5;

document.getElementById("num").value = num;
document.getElementById("pg").value = pg;

fetch('https://datacenter.taichung.gov.tw/Swagger/OpenData/566b84f1-dd8f-4e28-8da7-64e98ece837b')
    .then(response => response.json())
    .then(data => {
        arr = data;
        document.getElementById("totalRows").value = arr.length; // 更新唯讀文字框
        changePage(arr, pg, num);
    });

function changePage(arrObj, pg, num) {
    let cnt = document.getElementById("cnt");
    cnt.innerHTML = "";
    let tbl = document.createElement("table");
    let colName = Object.keys(arrObj[0]);
    let startIndex = (pg - 1) * num;
    let endIndex = pg * num;

    // 在超出總筆數範圍時，彈出警示
    if (startIndex < 0 || startIndex >= arrObj.length) {
        alert("您所輸入的頁數超出範圍，請重新輸入！");
        return;
    }

    if (endIndex > arrObj.length) endIndex = arrObj.length;

    let slicedArr = arrObj.slice(startIndex, endIndex);
    let tr = document.createElement("tr");
    for (let col = 0; col < colName.length; col++) {
        let th = document.createElement("th");
        th.innerHTML = colName[col];
        tr.appendChild(th);
    }
    tbl.appendChild(tr);

    for (let row = 0; row < slicedArr.length; row++) {
        let tr = document.createElement("tr");
        for (let col = 0; col < colName.length; col++) {
            let td = document.createElement("td");
            td.innerHTML = slicedArr[row][colName[col]];
            tr.appendChild(td);
        }
        tbl.appendChild(tr);
    }
    cnt.appendChild(tbl);

    tbl.addEventListener("click", (e) => {
        if (e.target.tagName == "TD" && e.target.cellIndex == 4)
            window.open(e.target.innerHTML, "", "width=500,height=400");
    });
}


let go = document.getElementById("go");
go.addEventListener("click", () => {
    pg = Number(document.getElementById("pg").value);
    num = Number(document.getElementById("num").value);
    localStorage.setItem("pg", pg);
    localStorage.setItem("num", num);
    changePage(arr, pg, num);
});

let prev = document.getElementById("prev");
prev.addEventListener("click", () => {
    pg = (pg > 1) ? pg - 1 : pg;
    document.getElementById("pg").value = pg;
    localStorage.setItem("pg", pg);
    changePage(arr, pg, num);
});

let next = document.getElementById("next");
next.addEventListener("click", () => {
    pg = (pg * num < arr.length) ? pg + 1 : pg;
    document.getElementById("pg").value = pg;
    localStorage.setItem("pg", pg);
    changePage(arr, pg, num);
});

let find = document.getElementById("find");
find.addEventListener("click", () => {
    document.getElementById("cnt").innerHTML = "";
    fetch('https://datacenter.taichung.gov.tw/Swagger/OpenData/566b84f1-dd8f-4e28-8da7-64e98ece837b')
        .then(response => response.json())
        .then(data => {
            let filtVal = document.getElementById("filt").value;
            let filteredData = data.filter(obj => obj["roadsection"].includes(filtVal));
            arr = (filteredData.length == 0) ? data : filteredData;
            document.getElementById("totalRows").value = arr.length; // 更新唯讀文字框
            pg = 1;
            localStorage.setItem("pg", pg);
            changePage(arr, pg, num);
        });
});

