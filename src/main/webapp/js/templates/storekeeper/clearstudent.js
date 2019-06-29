let clearStudentTemplate = `
<div id="return-Item-wrapper" class="card">
          <div class="card-head">
               <h4 class="text-center">Clear Student</h4>
          </div>
          <div class="card-body">
                 <div class="form-group">
                    <label>Registration No</label> 
                    <div id="reg-div">
                        <input type="text" id="idNo" class="form-control">
                        <button class="btn btn-primary" onclick="getStudentItems(event)">Search</button> 
                    </div>       
                 </div>
                 <div id="table-borrow">
                     <h3 class="text-center">Borrowed Items</h3> 
                     <table class="table table-bordered table-stripped">
                         <thead class="thead-dark">
                            <tr>
                              <th>Item Name</th>
                              <th>Amount</th>
                              <th>Date</th>  
                            </tr>            
                         </thead> 
                         <tbody id="tbody-borrow">
                           
                         </tbody>       
                     </table>
                 </div>
                 <div id="table-lost">
                     <h3 class="text-center">Lost Items</h3> 
                     <table class="table table-bordered table-stripped">
                         <thead class="thead-dark">
                            <tr>
                              <th>Item Name</th>
                              <th>Amount</th>
                              <th>Date</th>  
                            </tr>            
                         </thead> 
                         <tbody id="tbody-lost">
                           
                         </tbody>       
                     </table>
                 </div>
          </div>
          </div>

        
         <style>
          #return-Item-wrapper{
            padding: 30px;
            border-radius: 2%;
            width: 100%;
            }

            #reg-div{
              display: flex;
              justify-content: flex-start;
            }

            #reg-div .form-control{
               width: 30% !important;
            }
            
            #reg-div button{
               width: 20% !important;
               margin-left: 30px;
            }
          </style>`

var regInput
var studentCleared = true
function setupClearStudentTemplate(){
   regInput = document.querySelector("#idNo")
   $("#table-borrow").hide()
   $("#table-lost").hide()
}

function getStudentItems(e){
  e.preventDefault()
   $("#table-borrow").hide()
   $("#table-lost").hide()
   studentCleared = true
  if(regInput.value == ""){
    Swal.fire({
        position: 'center',
        type: 'error',
        title: "Reg No cannot be empty !",
        showConfirmButton: false,
        timer: 1500
      })
     return;
 }

 axios.get("/borroweditems?borrowId="+regInput.value)
 .then(data=>{
    
    if(data.data.length > 0){
      studentCleared = false
    }
    addToBorrowedTable(data.data)  

    fetchLost()
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

function fetchLost(){
  axios.get("/lostitems?id="+regInput.value)
  .then(data=>{
      
      if(data.data.length > 0){
        studentCleared = false
      }
      addToLostTable(data.data)  
 
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
function addToBorrowedTable(data){
  
    if(data.length > 0){
       $("#table-borrow").show()
      var tbody = document.querySelector("#tbody-borrow")
      tbody.innerHTML = "" 
      
      data.forEach(item => {
        
        var content = `<td class="tr-name" >${item.itemName}</td>
                        <td class="tr-qt" >${item.quantity}</td>
                        <td>${item.borrowedDate}</td>`
          var tr = document.createElement("tr")
          tr.innerHTML = content
          tbody.append(tr)  
                        
      });
   }
}

function addToLostTable(data){
  if(data.length > 0){
    $("#table-lost").show()
   var tbody = document.querySelector("#tbody-lost")
   tbody.innerHTML = "" 
   
   data.forEach(item => {
     
     var content = `<td class="tr-name" >${item.itemName}</td>
                     <td class="tr-qt" >${item.quantity}</td>
                     <td>${item.borrowedDate}</td>`
       var tr = document.createElement("tr")
       tr.innerHTML = content
       tbody.append(tr)  
                     
   });
 }
  console.log(studentCleared +" ll")
  if(studentCleared){
    Swal.fire({
      position: 'center',
      type : 'info',
      title : `Student cleared`,
      showConfirmButton: false,
      timer: 1500
  })
  }
}

