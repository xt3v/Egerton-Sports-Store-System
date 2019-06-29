let viewGameTemplate = `
<div id="tableWrapper" class="col-md-12">
<table id="gameTables" class="table table-bordered table-stripped">
  <thead class="thead-dark">
     <tr>
        <th>ID</th><th>Team</th><th>Sport</th><th>Date</th><th>Time</th>
        <th>Duration</th><th>Field</th>
     </tr>  
  </thead>
  <tbody id="tbody">
       
  </tbody>
</table>ball
</div>
</div>
<style>
  #tableWrapper{
    padding: 30px;
    background-color: white;
    border-radius: 2%;
 }
</style>
`
function setupViewGameTemplate(){
    axios.get(`/games`)
    .then(data=>{
        populateGameT(data.data)
    })

}


function populateGameT(data){
    var tbody = document.querySelector("#tbody")
    tbody.innerHTML = "" 
    data.forEach(game=>{
       var tr = document.createElement("tr")
       tr.innerHTML = `<td>${game.gameId}</td><td>${game.teamName}</td>
       <td>${game.sportName}</td><td>${game.date}</td><td>${game.actualTime}</td>
       <td>${game.duration}</td><td>${game.field.fieldName}</td>`
       //$(tr).attr("id",sport.sportId)
       tbody.appendChild(tr)
     })
     
     $("#gameTables").DataTable()
}