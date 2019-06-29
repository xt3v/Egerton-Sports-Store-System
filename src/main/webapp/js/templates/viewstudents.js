let viewStudentsTemplate = `
<div id="tableWrapper" class="col-md-12">
<table id="studentTable" class="table table-bordered table-stripped">
  <thead class="thead-dark">
     <tr>
        <th>Name</th><th>Reg No</th><th>Phone No</th><th>Residence</th><th></th>
     </tr>  
  </thead>
  <tbody id="tbody">
       
  </tbody>
</table>
</div>

<div class="modal fade" id="editStudentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg p-4" role="document">
  <div class="modal-content">
    <div class="modal-header">
      <h3 class="text-center">Edit Student</h3>   
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    
    <div class="modal-body p-4">
      <form id="editStudentForm" data-parsley-validate="">
         <div class="form-group">
            <label for="name">Name</label>
            <input class="form-control" type="text" name="name" id="studentName" required="true">   
        </div>

        <div class="form-group">
            <label for="regno">RegNo.</label>
            <input class="form-control" type="text" name="regno" id="studentRegNo" disabled="true">
        </div>
        
        <div class="form-group">
            <label for="regno">Residence</label>
            <input class="form-control" type="text" name="residence" id="studentResidence" required="true">
        </div>
        
        <div class="form-group">
            <label for="phoneNo">Phone No.</label>
            <input class="form-control" type="text" name="phoneNo" id="studentPhoneNo" required="true">
        </div>

        <div class="d-flex justify-content-center">
            <button class="btn btn-primary col-md-7">Save</button>
        </div>
       </form>
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      <button type="button" class="btn btn-primary">Save</button>
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

var viewStudentsTbody

function setupViewStudentsTemplate(){
    viewStudentsTbody = document.querySelector("#tbody")
    axios.get("/students")
    .then((res)=>{
     populateStudentsTable(res.data)
    })
    .catch(err=>{
        Swal.fire({
            position: 'center',
            type : 'error',
            title : `${err.response.message}`,
            showConfirmButton: false,
            timer: 1500
          })
    })

    $("#editStudentForm").on('submit',function(e){
      e.preventDefault();
      var form = $(this);

      form.parsley().validate();

      if (form.parsley().isValid()){
          submitEditForm(e)
      }
    })
}


function populateStudentsTable(data){
  viewStudentsTbody.innerHTML = ""
   data.forEach(student => {
        var tr = document.createElement("tr")
        $(tr).attr("id",student.regNo)
        let html = `<td class="st-name">${student.name}</td><td class="st-reg">${student.regNo}</td><td class="st-phone">${student.phoneNo}</td><td class="st-res">${student.residence}</td>
                    <td><button class="btn btn-danger ml-4" onclick="editStudentModal(event)">Edit</button></td>`
        tr.innerHTML = html
        viewStudentsTbody.appendChild(tr)            
   })

   $("#studentTable").DataTable()
}


function editStudentModal(e){
    var tr =  e.target.parentNode.parentNode  
    var name = $(tr).find(".st-name").html()
    var reg = $(tr).find(".st-reg").html()
    var phone = $(tr).find(".st-phone").html()
    var res  = $(tr).find(".st-res").html()

    $("#studentName").val(name)
    $("#studentRegNo").val(reg)
    $("#studentPhoneNo").val(phone)
    $("#studentResidence").val(res)
    $("#editStudentModal").modal('show')
}

function submitEditForm(e){
         //show alert
         Swal.fire({
          text: `Edit Student ${$("#studentName").val()}`,
          type: 'info',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes'
        }).then((result) => {
          if (result.value) {
             
              //send post request
              axios.put(`/students`,{
                  name :  $("#studentName").val(),
                  regNo :  $("#studentRegNo").val(),
                  residence : $("#studentResidence").val(),
                  phoneNo :$("#studentPhoneNo").val()
              })
              .then((rs)=>{
                  Swal.fire({
                      position: 'center',
                      type: 'success',
                      title: 'Student Edited',
                      showConfirmButton: false,
                      timer: 1500
                    })
                   //close modal
                   $("#editStudentModal").modal('hide')

                   setupViewStudentsTemplate()
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