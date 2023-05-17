const myList = document.getElementById("myList");
let count = myList.children.length + 1;

function append() {
    const newItem = document.createElement("li");
    newItem.innerHTML = "Item " + count;
    myList.appendChild(newItem);
    count++;
}

function remove() {
    const lastItem = myList.lastElementChild;
    if (lastItem) {
        lastItem.remove();
    }
}