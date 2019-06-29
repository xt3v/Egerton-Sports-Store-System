let requestedItemsTemplate = `
<div id="tableWrapper" class="col-md-12">
<table id="sportsTable" class="table table-bordered table-stripped">
  <thead class="thead-dark">
     <tr>
         <th>ID</th> <th>Team id</th> <th>Date</th> <th>Time</th> <th>Items</th>
     </tr>  
  </thead>
  <tbody id="tbody">
       
  </tbody>
</table>
</div>

<style>
  #tableWrapper{
    padding: 30px;
    background-color: white;
    border-radius: 2%;
 }
</style>
`


function setupRequestItemTemplate(){
    axios.get('/requesteditems')
    .then(data=>{
        populateItTable(data.data)
    })
}

function populateItTable(data){
  
    var tbody = document.querySelector("#tbody")
    let itemsS = ""
    tbody.innerHTML = "" 
    data.forEach(item=>{

       var tems = ""
       item.items.forEach(f=>{
           tems += `${f.name}:${f.amount}<br>\n`
       })
       var tr = document.createElement("tr")
       tr.innerHTML = `<td>${item.id}</td> <td>${item.game.teamId}</td> <td>${item.game.date}</td>
       <td>${item.game.actualTime}</td> <td>${tems}</td>
     `
       tbody.appendChild(tr)
     })
     
     $("#sportsTable").DataTable()
}