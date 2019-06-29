let selectIssueItemsTemplate = `
<div id="issue-item-wrapper" class="col-md-8">
<h3 class="text-center" id="heading">Choose Items</h3>

<div id="inputDiv" class="d-flex justify-content-center mt-4">
    <select class="flexselect float-left form-control" style="width:30%;">
        
    </select>
    <input type="number" id="itemAmount" class="form-control float-left ml-4" style="width:10%;" min="1">
    <i onclick="addIssueItem()" style="color:blue;" class="fas fa-2x fa-plus-circle float-left ml-4 addbtn"></i>
</div>
<div class="items-list">
    <table class="table table-bordered table-stripped">
        <thead class="thead-dark">
           <tr>
             <th>Item Name</th>
             <th>Amount</th>
             <th></th>  
           </tr>            
        </thead> 
        <tbody id="tbody">
          
        </tbody>       
       </table>
</div>
<div id="issueDiv" class="row d-flex justify-content-center" style="clear:both;">
    <div class="col-md-4 d-flex justify-content-center">
        <button id="issueBtn" class="btn btn-primary" style="width:80%;" onclick="issueItems()">Issue</button>
    </div>
</div>
</div>
<style>
#issue-item-wrapper{
 background-color: white;
 padding: 30px;
 border-radius: 5%;
}

.items-list{
 padding: 60px;
}

</style>      
`
var itemsIssueList
var selectedItems = []
var itemInput
var itemAmount
var items 

function addIssueItem(){
  var itemName = itemInput.options[itemInput.selectedIndex].text
  var amount = itemAmount.value
  var id = itemInput.options[itemInput.selectedIndex].value
  if(selectedItems.indexOf(id) > -1){
    Swal.fire({
        position: 'center',
        type : 'error',
        title : `Item already selcted !`,
        showConfirmButton: false,
        timer: 1500
    })
    return;
  }

  if(!isAvailable(id,amount)){
    return;
  }else if(itemAmount != undefined && amount != undefined && itemName != "" && amount != "" && amount != 0){
    var content = `
    <td class="itemN" itemId="${id}">${itemName}</td>
    <td class="itemA">${amount}</td>
    <td>
        <i class="fas fa-2x fa-minus-circle text-danger" onclick="removeIssueItem(event)"></i>
    </td>`     
    
    var tr = document.createElement("tr")
    tr.innerHTML = content
    itemsIssueList.appendChild(tr)
    document.querySelector("#issueBtn").disabled = false
    selectedItems.push(id)
}else{
    var msg = "Please select Item name"
    if(amount < 1 || amount != "")  msg = "Amount must be greater than 1"
    Swal.fire({
        position: 'center',
        type : 'error',
        title : `${msg}`,
        showConfirmButton: false,
        timer: 1500
    })
  }

}

function issueItems(){
   var data = []  
    
   document.querySelectorAll("#tbody tr").forEach(tr=>{
         var item = {
             itemId :$(tr).find(".itemN").attr('itemId'),
             amount : $(tr).find(".itemA").html()
         } 
         data.push(item)
   })
    
    axios.post("/issueitems",{
        items : data,
        borrowerId : borrowerId,
        status : borrowerStatus 
    })
    .then(data=>{
        Swal.fire({
            position: 'center',
            type : 'success',
            title : `Issued Items`,
            showConfirmButton: false,
            timer: 1500
        })
       
        document.querySelector("#issueBtn").style.display = "none"
        document.querySelectorAll(".fa-minus-circle ").forEach(fa=>{
           fa.style.display = "none"   
        })
        $("#inputDiv").remove()
        document.querySelector("#heading").innerHTML = "Issued Items"
        document.querySelector("#issueDiv").style.display = "none"

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

function removeIssueItem(e){
    var id = $(e.target.parentNode.parentNode).attr('itemId')
    e.target.parentNode.parentNode.remove()

    if(itemsIssueList.children.length < 1){
         document.querySelector("#issueBtn").disabled = true
    }

    selectedItems.splice(selectedItems.indexOf(id),1)
}

function setupSelectIssueItemsTemplate(){ 
   itemsIssueList = document.querySelector("#tbody")

   axios.get("/items?sportId="+selectedSportId)
   .then(data=>{
       items = data.data
       setItemSelect(data.data)
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

   document.querySelector("#issueBtn").disabled = true
   itemInput = document.querySelector(".flexselect")
   itemAmount = document.querySelector("#itemAmount")
   selectedItems = []
}


function setItemSelect(data){
   var select = document.querySelector(".flexselect")

   var content = "<option> </option>"

   data.forEach(item => {
      content += `<option value="${item.itemId}">${item.name}</option>`  
   });

   select.innerHTML = content

   $(select).flexselect()
}

function isAvailable(id,amount){
   
   for(var i = 0;i < items.length;i++){
    var item = items[i] 
    if(item.itemId  == id){
        if(amount > item.quantity){
          Swal.fire({
              position: 'center',
              type : 'error',
              title : `Only ${item.quantity} are available!`,
              showConfirmButton: false,
              timer: 1500
          })
          return false
        }
     }
   }
   return true
}