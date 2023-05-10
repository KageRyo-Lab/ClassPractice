let arr = [1, 2, 3, 4, 5, 6, 7, 8, 9];
for (i = 0; i < arr.length; i++) {
    let randNum = Math.floor(Math.random() * arr.length);
    console.log("隨機抽亂數:", randNum);
    [arr[i], arr[randNum]] = [arr[randNum], arr[i]];
    console.log("打亂陣列:", arr);
}
for (i = 1; i < arr.length + 1; i++) {
    document.querySelector(`div:nth-child(${i})`).innerHTML = arr[i - 1];
}