let viewSportsTemplate = `
<div id="tableWrapper" class="col-md-12">
<table id="sportsTable" class="table table-bordered table-stripped">
  <thead class="thead-dark">
     <tr>
         <th>ID</th><th>NAME</th><th></th>
     </tr>  
  </thead>
  <tbody id="tbody">
       
  </tbody>
</table>
</div>

<div class="modal fade" id="editSportModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg p-4" role="document">
  <div class="modal-content">
    <div class="modal-header">
      <h3 class="text-center">Edit Sport</h3>   
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    
    <div class="modal-body p-4">
         <form class="form" id="sportEditForm">
            <div class="form-group">
                 <label class="label">Name</label>
                 <input type="text" class="form-control" id="sportName" required="true">
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

var editedSportId = -1
function setupViewSportsTemplate(){
    axios.get("/sports")
    .then(data=>{
        setViewSportsTable(data.data)
    })
    .catch(err=>{
        console.log(err)
    })

    $("#sportEditForm").on('submit',function(e){
      e.preventDefault();
      var form = $(this);

      form.parsley().validate();
      if (form.parsley().isValid()){
          submitSportEditForm(e)
      }
    })
}

function setViewSportsTable(data){
   var tbody = document.querySelector("#tbody")
   tbody.innerHTML = "" 
   data.forEach(sport=>{
      var tr = document.createElement("tr")
      tr.innerHTML = `<td>${sport.sportId}</td><td class="sport-name" >${sport.name}</td>
        <td>
           <button class="btn btn-danger ml-4" onclick="editSportModal(event)">Edit</button>
      </td>`
      $(tr).attr("id",sport.sportId)
      tbody.appendChild(tr)
    })
    
    $("#sportsTable").DataTable()
}


function editSportModal(e){
    var tr = e.target.parentNode.parentNode
    $("#editSportModal").modal("show")
    editedSportId = $(tr).attr("id")
    $("#sportName").val($(tr).find(".sport-name").html())
}


function submitSportEditForm(e){
     //show alert
     Swal.fire({
      text: `Edit Sport`,
      type: 'info',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes'
    }).then((result) => {
      if (result.value) {
         
          //send post request
          axios.put(`/sports`,{
              name :  $("#sportName").val(),
              sportId : editedSportId
          })
          .then((rs)=>{
              Swal.fire({
                  position: 'center',
                  type: 'success',
                  title: 'Sport Edited',
                  showConfirmButton: false,
                  timer: 1500
                })
               //close modal
               $("#editSportModal").modal('hide')

               setupViewSportsTemplate()
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