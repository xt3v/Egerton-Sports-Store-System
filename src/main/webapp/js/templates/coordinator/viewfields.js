let viewFieldsTemplate = `
<div id="tableWrapper" class="col-md-12">
<table id="fieldTable" class="table table-bordered table-stripped">
  <thead class="thead-dark">
     <tr>
         <th>ID</th><th>NAME</th> <th>SPORT</th> <th></th>
     </tr>  
  </thead>
  <tbody id="tbody">
       
  </tbody>
</table>
</div>

<div class="modal fade" id="editFieldModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg p-4" role="document">
  <div class="modal-content">
    <div class="modal-header">
      <h3 class="text-center">Edit Field</h3>   
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    
    <div class="modal-body p-4">
         <form class="form" id="fieldEditForm">
            <div class="form-group">
                 <label class="label">Name</label>
                 <input type="text" class="form-control" id="fieldName" required="true">
            </div>

            <div class="form-group">
                 <label>Sport</label>
                 <select id="sportSelect" class="form-control">
                      
                 </select>
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

var editedFielId = -1
function setupViewFieldsTemplate(){
    axios.get("/fields")
    .then(data=>{
        setViewFieldsTable(data.data)
    })
    .catch(err=>{
        console.log(err)
    })
    
    axios.get("/sports")
    .then(data=>{
        setupSportsEditField(data.data)
    })
    .catch(err=>{
      console.log(err)
    })
    $("#fieldEditForm").on('submit',function(e){
      e.preventDefault();
      var form = $(this);

      form.parsley().validate();
      if (form.parsley().isValid()){
          submitFieldEditForm(e)
      }
    })
}

function setupSportsEditField(data){
    let html = ""
    data.forEach(sport => {
        html += `<option value="${sport.sportId}">${sport.name}</option>`
    })

    var sportSelect = document.querySelector("#sportSelect")
    sportSelect.innerHTML = html

}


function setViewFieldsTable(data){
   var tbody = document.querySelector("#tbody")
   tbody.innerHTML = "" 
   data.forEach(field=>{
      var tr = document.createElement("tr")
      tr.innerHTML = `<td>${field.fieldId}</td><td class="field-name" >${field.fieldName}</td> <th class="field-sport" id="${field.sportId}">${field.sportName}</th>
        <td>
           <button class="btn btn-danger ml-4" onclick="editFieldModal(event)">Edit</button>
      </td>`
      $(tr).attr("id",field.fieldId)
      tbody.appendChild(tr)
    })
    $("#fieldTable").DataTable()
    
}


function editFieldModal(e){
    var tr = e.target.parentNode.parentNode
    $("#editFieldModal").modal("show")
    editedFielId = $(tr).attr("id")
    var spId = $(tr).find(".field-sport").attr("id")
    $("#sportSelect").val(spId)
    $("#fieldName").val($(tr).find(".field-name").html())
}


function submitFieldEditForm(e){
     //show alert
     Swal.fire({
      text: `Edit Field`,
      type: 'info',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes'
    }).then((result) => {
      if (result.value) {
         
          //send post request
          axios.put(`/fields`,{
              fieldName :  $("#fieldName").val(),
              fieldId : editedFielId,
              sportId : $("#sportSelect").val(),
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
               $("#editFieldModal").modal('hide')

               setupViewFieldsTemplate()
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