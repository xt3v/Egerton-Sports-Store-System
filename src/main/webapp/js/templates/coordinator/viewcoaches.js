let viewCoachTemplate = `
<div id="tableWrapper" class="col-md-12">
<table id="coachTable" class="table table-bordered table-stripped">
  <thead class="thead-dark">
     <tr>
        <th>Name</th><th>Employee Id</th><th>Team Name</th><th>Sport</th><th></th>
     </tr>  
  </thead>
  <tbody id="tbody">
       
  </tbody>
</table>
</div>

<div class="modal fade" id="editCoachModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg p-4" role="document">
  <div class="modal-content">
    <div class="modal-header">
      <h3 class="text-center">Edit Coach</h3>   
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    
    <div class="modal-body p-4">
         <form class="form" id="coachEditForm">
            <div class="form-group">
                 <label class="label">Name</label>
                 <input type="text" class="form-control" id="coachName" required="true">
            </div>

            <div class="d-flex justify-content-center">
               <input type="submit" class="btn btn-primary col-md-7" value="save">
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
</style>`

var editedCoachId = -1
function setupViewCoachTemplate(){
    axios.get("/coaches")
    .then(data=>{
        setViewCoachTable(data.data)
    })
    .catch(err=>{
        console.log(err)
    })

    $("#coachEditForm").on('submit',function(e){
      e.preventDefault();
      var form = $(this);

      form.parsley().validate();
      if (form.parsley().isValid()){
          submitCoachEditForm(e)
      }
    })
}

function setViewCoachTable(data){
   var tbody = document.querySelector("#tbody")
   tbody.innerHTML = "" 
   data.forEach(coach=>{
      var tr = document.createElement("tr")
      tr.innerHTML = `<td class="coach-name">${coach.name}</td><td>${coach.employeeId}</td><td>${ coach.teamName }</td> <td>${coach.sportName}</td> <td>
           <button class="btn btn-danger ml-4" onclick="editCoachModal(event)">Edit</button>
      </td>`
      $(tr).attr("id",coach.employeeId)
      tbody.appendChild(tr)
    })

    $("#coachTable").DataTable()
      
}


function editCoachModal(e){
    var tr = e.target.parentNode.parentNode
    $("#editCoachModal").modal("show")
    editedCoachId = $(tr).attr("id")
    $("#coachName").val($(tr).find(".coach-name").html())
}


function submitCoachEditForm(e){
     //show alert
     Swal.fire({
      text: `Edit Coach ${editedCoachId}`,
      type: 'info',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes'
    }).then((result) => {
      if (result.value) {
         
          //send post request
          axios.put(`/coaches`,{
              name :  $("#coachName").val(),
              employeeId : editedCoachId
          })
          .then((rs)=>{
              Swal.fire({
                  position: 'center',
                  type: 'success',
                  title: 'Coach Edited',
                  showConfirmButton: false,
                  timer: 1500
                })
               //close modal
               $("#editCoachModal").modal('hide')

               setupViewCoachTemplate()
          })
          .catch((err)=>{
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
    }) 
}