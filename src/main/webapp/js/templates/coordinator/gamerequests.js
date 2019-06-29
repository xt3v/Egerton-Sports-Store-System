let gameRequestTemplate = `
<div id="tableWrapper" class="col-md-12">
<table id="gameTable" class="table table-bordered table-stripped">
  <thead class="thead-dark">
     <tr>
        <th>Team Name</th><th>Sport</th><th>Date</th><th>Time</th>
        <th>Duration</th><th>Requester Id</th><th></th>
     </tr>  
  </thead>
  <tbody id="tbody">
       
  </tbody>
</table>
</div>
</div>

<div class="modal fade" id="editRequest" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg p-4" role="document">
  <div class="modal-content">
    <div class="modal-header">
      <h3 class="text-center">Game Request</h3>   
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    
    <div class="modal-body p-4">
         <form class="form row justify-content-center" id="setReqForm">
             <div class="col-md-8">
                <div class="form-group">
                <label id="fieldLabel">Available Fields</label>
                <select id="fieldSelect" class="form-control">
                    
                </select>
             </div>         
             <div class="row justify-content-center">
                <button class="btn btn-primary" id="acceptBtn" onclick="acceptGame(event)">Accept</button>
                <button class="btn btn-danger ml-2" id="rejectBtn" onclick="rejectGame(event)">Reject</button>
             </div>
            </div>
         </form>  
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
    </div>
  </div>
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
var gameRequestId;
function setupGameRequestTemplate(){
   
   axios.get("/gamerequest")
   .then(data=>{
     addToGamesRequestTable(data.data)     
   })
   .catch(err=>{
       console.log(err)
   })
}

function addToGamesRequestTable(data){
    var tbody = document.querySelector("#tbody")
    tbody.innerHTML = "" 
    data.forEach(game=>{
       var tr = document.createElement("tr")
       tr.innerHTML = `<td>${game.team.name}</td><td>${game.team.sportName}</td>
       <td>${game.date}</td><td>${game.time}</td><td>${game.duration}</td><td>${game.requesterId}</td><td>
         <button class="btn btn-primary" onclick="getAvailableFields(event)">Set</button>
       </td>`
       $(tr).attr("id",game.id)
       tbody.appendChild(tr)
     })
    $("#gameTable").DataTable()
}

function getAvailableFields(e){
     var tr = e.target.parentNode.parentNode
     var gameId = $(tr).attr("id")
     gameRequestId = gameId
     axios.get(`/fields?req=${gameId}`)
     .then(data=>{
        setupSportsChoices(data.data)
     })
     .catch(err=>{
         console.log(err)
     })

    
}

function setupSportsChoices(data){
   if(data.length > 0){
        $("#fieldLabel").html("Available fields")
        document.getElementById("acceptBtn").disabled = "false"
        let html = ""
        data.forEach(field => {
            html += `<option value="${field.fieldId}">${field.fieldName}</option>`
        })

        var sportSelect = document.querySelector("#fieldSelect")
        sportSelect.disabled = false
        document.getElementById("acceptBtn").disabled = false
        sportSelect.innerHTML = html
   }else{
        var sportSelect = document.querySelector("#fieldSelect")
        sportSelect.disabled = true
        $("#fieldLabel").html("No fields available")
        document.getElementById("acceptBtn").disabled = true
   }

   $("#editRequest").modal("show")

}

function acceptGame(e){
    e.preventDefault()
    var id = $("#fieldSelect").val() 
    axios.post(`/games`,{
        requestId : gameRequestId,
        fieldId  : id
    })
    .then(data=>{
        Swal.fire({
            position: 'center',
            type: 'success',
            title: 'Game Accepted',
            showConfirmButton: false,
            timer: 1500
          })

          $("#editRequest").modal("hide")
          setupGameRequestTemplate()
    })
    .catch(err=>{
        let message = err.response.data.message
        Swal.fire({
            position: 'center',
            type: 'error',
            title: message,
            showConfirmButton: false,
            timer: 1500
          })
    })
}

function rejectGame(e){
    e.preventDefault()
    axios.delete(`/gamerequest?id=${gameRequestId}`)
    .then(data=>{
        Swal.fire({
            position: 'center',
            type: 'success',
            title: 'Rejected Game',
            showConfirmButton: false,
            timer: 1500
          })

          $("#editRequest").modal("hide")
          setupGameRequestTemplate()
    })
    .catch(err=>{
        let message = err.response.data.message
        Swal.fire({
            position: 'center',
            type: 'error',
            title: message,
            showConfirmButton: false,
            timer: 1500
          })

    })
}