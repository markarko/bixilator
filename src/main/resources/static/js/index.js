let form = document.querySelector("#search-form");

form.addEventListener("submit", (event) => {
    let searchQuery = document.querySelector("#search-input").value;
    event.preventDefault();
    fetch("/search/?" + new URLSearchParams({ "query": `${searchQuery}`}), {
        method : "GET",
        headers : {
            "Content-Type": "application/json;charset=UTF-8",
            "Accept": "application/json"
        }
    })
        .then((res) => {
            return res.json();
            //console.log(res);
        })
        .then((json) => {
            console.log(json);
        })
        .catch((err) => {
            console.log(err);
        });
});