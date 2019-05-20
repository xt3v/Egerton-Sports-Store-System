

let issueItemTemplate = `
<div class="col-md-7" id="issue-item-wrapper">
<h4 class="text-center">Issue Item</h4>
<div class="form-group">
   <label for="identification" style="width:40%;">Enter Reg No/Empployee ID</label>
   <div class="d-flex">
     <input type="text" style="width:40%;" class="form-control" id="identification">
     <button class="btn btn-primary ml-5" id="searchBtn">Search</button>
   </div>
</div>
<div id="table-div">
</div>

</div>

<style>
    #issue-item-wrapper{
        background-color: white;
        height: 300px;
        border-radius: 5%;
        padding : 40px;
        height : 400px;
    }
</style>`

var inputReg
let sports
let tableWrapper
function search(){
     if(inputReg.value == ""){
        Swal.fire({
            position: 'center',
            type: 'error',
            title: "Reg No/Employee cannot be empty !",
            showConfirmButton: false,
            timer: 1500
          })
         return;
     }

     axios.get("/search?borrowId="+inputReg.value)
     .then(data=>{
         addToTable(data.data)
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

function setupIssueItemTemplate(){
     inputReg = document.querySelector("#identification")
     
     document.querySelector("#searchBtn").onclick = search

     tableWrapper = document.querySelector("#table-div")

    //get sports to populate entry 
    axios.get("/sports")
    .then((res)=>{
       sports = res.data
     })
}

function addToTable(data){
    //populate the select
    
    let html = ""
    sports.forEach(sport => {
        html += `<option value="${sport.sportId}">${sport.name}</option>`
    })

   let content =  `<table class="table table-bordered table-stripped">
   <thead class="thead-dark">
      <tr>
        <th>NAME</th>
        <th>ID/REG NO</th>
        <th>STATUS</th>  
      </tr>            
   </thead> 
   <tbody id="tbody">
      <tr>
         <td>${data.name}</td>
         <td>${data.id}</td>
         <td>${data.status}</td>
      </tr>    
   </tbody>       
   </table>
   <label>Sport</label>
   <div>
     <select style="width:40%;" class="float-left form-control" name="selectSport" id="selectSport">
        ${html}
     </select>
   
     <button class="btn btn-primary float-right" onclick="selectItems()" >GO</button>
   </div>`

   tableWrapper.innerHTML = content
   
   //setup variables for selectitem page

   borrowerId = data.id
   borrowerStatus = data.status

}

function selectItems(){
    selectedSportId = document.querySelector("#selectSport").value

    selectIssueItems()
    
}
