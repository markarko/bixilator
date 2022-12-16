let form = document.querySelector("#search-form");
let map;
let markers = [];

document.querySelector("#map-script").setAttribute("src", `https://maps.googleapis.com/maps/api/js?key=${apiKey}&callback=initMap`);

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
        })
        .then((json) => {
            for (let obj of json){
                createMarker(new google.maps.LatLng(obj.latitude, obj.longitude), obj.name);
            }
            console.log(json);
        })
        .catch((err) => {
            console.log(err);
        });
});

function initMap() {
    let options = {
        center: { lat: 45.539385081961676, lng: -73.54099988937377 },
        zoom: 12
    }
    map = new google.maps.Map(document.getElementById("map"), options);
}

function createMarker(pos, title){
    markers.push(
    new google.maps.Marker({
        position: pos,
        map,
        title: `${title}`,
    }));
}

function clearMarkers(){
    for(let marker of markers){
        marker.setMap(null);
    }
}