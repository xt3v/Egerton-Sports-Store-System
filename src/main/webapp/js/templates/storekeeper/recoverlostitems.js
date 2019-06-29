let recoverLostItemsTemplate = `
<div id="return-Item-wrapper" class="card">
          <div class="card-head">
               <h4 class="text-center">Lost Items</h4>
          </div>
          <div class="card-body">
                 <div class="form-group">
                    <label>Registration/Employee Id</label> 
                    <div id="reg-div">
                        <input type="text" id="idNo" class="form-control">
                        <button class="btn btn-primary" onclick="getUserLostItems(event)">Search</button> 
                    </div>       
                 </div>
                 <div id="table-div">
                     <table class="table table-bordered table-stripped">
                         <thead class="thead-dark">
                            <tr>
                              <th>Item Name</th>
                              <th>Amount</th>
                              <th>Date</th>
                              <th></th>  
                            </tr>            
                         </thead> 
                         <tbody id="tbody">
                           
                         </tbody>       
                     </table>
                 </div>
          </div>
          </div>

          <div class="modal fade" id="returnModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h3 id="returnItemName" class="text-center">Item Name</h3>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                        <div class="form-group">
                           <h4 class="text-center">Lost Amount : <label id="lostAmount">3</label>  
                           </h4>
                           <div style="display:flex;justify-content: center;">
                             <label><h6>Returned Amount</h6></label>
                             <input style="width:40%;margin-left: 30px;" class="form-control" type="number" min="1" id="returnedAmount">
                           </div>
                        </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="saveReturnedLostItems()">Save</button>
                  </div>
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

var lostItemsList = []

function setupRecoverLostItemsTemplate(){
  regInput = document.querySelector("#idNo")
}


function getUserLostItems(e = null){
  if(e != null) e.preventDefault()
  if(regInput.value == ""){
    Swal.fire({
        position: 'center',
        type: 'error',
        title: "Reg No/Employee cannot be empty !",
        showConfirmButton: false,
        timer: 1500
      })
     return;
 }

 axios.get("/lostitems?id="+regInput.value)
 .then(data=>{
    lostItemsList = data.data
    addToReturnLostTable(data.data)  
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


function addToReturnLostTable(data){
  var tbody = document.querySelector("#tbody")
  tbody.innerHTML = "" 
    if(data.length == 0){
      Swal.fire({
        position: 'center',
        type: 'success',
        title: "No Borrowed Items!",
        showConfirmButton: false,
        timer: 1500
      })
      return
    } 
   
    data.forEach(item => {
        var content = `<td class="tr-name" >${item.itemName}</td>
                      <td class="tr-qt" >${item.quantity}</td>
                      <td>${item.borrowedDate}</td>
                      <td>
                          <button itemId="${item.lostItemId}" class="return-btn btn btn-primary" onclick="returnLostModal(event)">return</button>
                      </td>`
        var tr = document.createElement("tr")
        tr.innerHTML = content
        tbody.append(tr)                
    });

}

function returnLostModal(e){
  var tr = e.target.parentNode.parentNode
  var name = $(tr).find(".tr-name").html()
  var amount = $(tr).find(".tr-qt").html()
  document.querySelector("#returnItemName").innerHTML = name
  document.querySelector("#lostAmount").innerHTML = amount
  $("#returnModal").modal("show") 

}


function saveReturnedLostItems(){
    var val = $("#returnedAmount").val()
    var amount = parseInt(document.querySelector("#lostAmount").innerHTML)
    
    var msg = ""
    if(val > amount){
        msg = `Amount cannot be greater than ${amount}`
    }else if(val < 1){
       msg = "Amount cannot be lesser than 1"
    }

    if(msg != ""){
      Swal.fire({
        position: 'center',
        type : 'error',
        title : msg,
        showConfirmButton: false,
        timer: 1500
       })
       return
    }

    var name = document.querySelector("#returnItemName").innerHTML
    
    lostItemsList.forEach(item=>{
       if(item.itemName == name){
          axios.put("/lostitems",{
            lostItemId : item.lostItemId,
            itemId: item.itemId,
            borrowedDate : item.borrowedDate,
            quantity : (amount-val),
            borrowerId: item.borrowerId,
            itemName : item.itemName,
            recoveredAmount : val
          })
          .then(data=>{
            Swal.fire({
              position: 'center',
              type : 'success',
              title : `Recovered ${val} items`,
              showConfirmButton: false,
              timer: 1500
             })

             getUserLostItems()
             $("#returnModal").modal("hide") 
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
    })

}