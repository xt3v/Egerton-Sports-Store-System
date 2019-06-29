let viewTeamsTemplate = `<div id="sportwrapper" style="height:400px;" class="col-md-9 d-flex flex-column justify-content-center align-items-center">
              <h3>Choose Sport</h3>
              <div class="col-md-6 text-center mt-3 pd-3">
                 <select id="sportSelect" class="flexselect form-control">

                 </select>
                 <button class="btn btn-primary" style="margin-top:20px;width:50%;" onclick="viewTeamsTable()">
                    VIEW TEAMS
                 </button>
              </div>
           </div>
          
           <div id="tableWrapper" class="col-md-12">
            <table id="itemTable" class="table table-bordered table-stripped">
              <thead class="thead-dark">
                 <tr>
                    <th>Name</th><th>Coach Name</th><th>Coach ID</th><th>Captain Name</th><th>Captain Reg</th><th>Gender</th><th></th>
                 </tr>  
              </thead>
              <tbody id="tbody">
                   
              </tbody>
            </table>
          </div>

          <div class="modal fade" id="stockModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h3>Add Stock  :<br><span id="stockItemName" class="text-center"></span></h3>   
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                    <div class="form-group">
                       <div style="display:flex;justify-content: center;">
                         <label><h6>Amount Added</h6></label>
                         <input style="width:40%;margin-left: 30px;" class="form-control" type="number" min="1" id="addedAmount">
                       </div>
                    </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="addStockItems()">Save</button>
              </div>
            </div>
          </div>
        </div>


        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h3 class="text-center">Edit Team </h3>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            
            <div class="modal-body">
                <form class="form" id="editTeamForm" data-parsley-validate="">
                        <div class="form-group">
                            <label for="name">Name : </label>
                            <input class="form-control" type="text" id="teamName" required="true">
                          </div>
                          <div class="form-group">
                            <label for="captainRegNo">Captain RegNo: </label>
                            <input class="form-control" type="text" id="captainRegNo" required="true">
                          </div>
                          <div class="form-group">
                            <label for="coachId">Coach Employee Id :</label>
                            <input class="form-control" type="text" id="coachId" required="true">
                          </div>
                       <div class="form-group">
                          <label style="width:20%;display:inline;">Male :</label>
                          <input class="" style="width:10%;display:inline;height:20px;" type="radio" id="gender" name="gender" required="true" value="male">
                      </div>
                      <div class="form-group">
                          <label style="width:20%;display:inline;">Female :</label>
                          <input class="" style="width:10%;display:inline;height:20px;" type="radio" id="gender" name="gender" required="true" value="female">
                      </div>
                      <div class="d-flex flex-column align-items-center">
                          <button class="btn btn-primary" style="width:40%;">
                              Save
                          </button>
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
             #sportwrapper,#tableWrapper{
                padding: 30px;
                background-color: white;
                border-radius: 2%;
             }
           </style>`


var editTeamId = -1

function setupViewTeamsTemplate(){
   $("#tableWrapper").hide()

   axios.get("/sports")
   .then((res)=>{
      populateTeamSports(res.data)
   })
   
   $("#editTeamForm").on('submit',function(e){
      e.preventDefault();
      var form = $(this);

      form.parsley().validate();

      if (form.parsley().isValid()){
          editTeam()
      }
   })
}
$("#tableWrapper").hide()


function populateTeamSports(data){
    let html = ""
    data.forEach(sport => {
        html += `<option value="${sport.sportId}">${sport.name}</option>`
    })

     var sportSelect = document.querySelector("#sportSelect")
     sportSelect.innerHTML = html

    $(sportSelect).flexselect()
}

function viewTeamsTable(re = false){
   if(!re) selectedSportId = $("#sportSelect").val()
   axios.get("/teams?sportId="+selectedSportId)
   .then(data=>{
       setTeamsTable(data.data)
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

}


function setTeamsTable(data){
   var tbody = document.querySelector("#tbody")
   tbody.innerHTML = ""

   data.forEach(team =>{
       var tr = document.createElement("tr")
       
       tr.innerHTML = `
        <td class="teamName" >${team.name}</td><td>${team.coachName}</td><td class="coachId" >${team.coachId}</td><td>${team.captainName}</td><td class="captainReg">${team.captainRegNo}</td><td class="gender">${team.gender}</td>
        <td class="editTeam"  teamid="${team.teamId}" ><button class="btn btn-danger" onclick="editTeamModal(event)" >Edit</button></td>`
       tbody.appendChild(tr)
   })
   $('#itemTable').DataTable()
   $("#sportwrapper").remove()
   $("#tableWrapper").show()
}

function editTeamModal(e){
   var tr = e.target.parentNode.parentNode
   editTeamId = $(tr).find(".editTeam").attr("teamid")
   var name = $(tr).find(".teamName").html()
   var coachId = $(tr).find(".coachId").html()
   var studentReg = $(tr).find(".captainReg").html()
   var gender = $(tr).find(".gender").html()
   
   $("#teamName").val(name)
   $("#coachId").val(coachId)
   $("#captainRegNo").val(studentReg)
   $(`input[name="gender"][value="${gender}"]`).prop('checked',true)
   $("#editModal").modal("show")   
}

function editTeam(){
  Swal.fire({
    text: `Edit Team`,
    type: 'info',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes'
  })
  .then((rs)=>{
    if(rs.value){
        axios.put("/teams",{
          name : $("#teamName").val(),
          gender : $("input[name='gender']:checked").val(),
          captainRegNo : $("#captainRegNo").val(),
          coachId : $("#coachId").val(),
          sportId : selectedSportId,
          teamId : editTeamId
      })
      .then(data=>{
        Swal.fire({
          position: 'center',
          type: 'success',
          title: 'Team Edited',
          showConfirmButton: false,
          timer: 1500
        })
        $("#editModal").modal("hide")  
        viewTeamsTable(true)
      })
      .catch(err=>{
        
          Swal.fire({
            position: 'center',
            type : 'error',
            title : `${err.response.data.message}`,
            showConfirmButton: false,
            timer: 1500
        })

       })
     }  
  })


}
