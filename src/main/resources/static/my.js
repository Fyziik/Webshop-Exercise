let titles = document.getElementsByTagName('h1');

for (let i = 0; i < titles.length; i++) {
    addBehavior(titles[i])
}

function addBehavior(e) {
    e.addEventListener('click',() => {
        alert("Clicked")
    })

}