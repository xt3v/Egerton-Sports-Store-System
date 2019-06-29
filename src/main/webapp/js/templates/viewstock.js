  let viewStockTemplate = `<div id="sportwrapper" style="height:400px;" class="col-md-9 d-flex flex-column justify-content-center align-items-center">
              <h3>Choose Sport</h3>
              <div class="col-md-6 text-center mt-3 pd-3">
                 <select id="sportSelect" class="flexselect form-control">

                 </select>
                 <button class="btn btn-primary" style="margin-top:20px;width:50%;" onclick="viewStockTable()">
                    VIEW STOCK
                 </button>
              </div>
           </div>
          
           <div id="tableWrapper" class="col-md-12">
            <table id="itemTable" class="table table-bordered table-stripped">
              <thead class="thead-dark">
                 <tr>
                    <th>Name</th><th>Amount In Stock</th><th>Borrowed Amount</th><th>Lost Amount</th><th></th>
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
              <h3 class="text-center">Edit Item</h3>   
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            
            <div class="modal-body">
                 <div class="form-group p-3">
                    <label for="editName">Name</label>
                    <input id="editName" type="text" class="form-control">  
                 </div> 

                 <div class="form-group">
                    <label>Sport</label>
                    <select id="editSelectSport" class="flexselect form-control">

                    </select>  
                 </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" onclick="editStockItem()">Save</button>
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

var stockItems = []
var stockItemId = -1
function setupViewStockTemplate(){
   stockItems = []
   $("#tableWrapper").hide()

   axios.get("/sports")
   .then((res)=>{
     populateSports(res.data)
   })
  
}


function populateSports(data){
    let html = ""
    data.forEach(sport => {
        html += `<option value="${sport.sportId}">${sport.name}</option>`
    })

     var sportSelect = document.querySelector("#sportSelect")
     sportSelect.innerHTML = html
     document.getElementById("editSelectSport").innerHTML = html

    $(sportSelect).flexselect()
}

function viewStockTable(re = false){
   if(!re) selectedSportId = $("#sportSelect").val()
   axios.get("/items?sportId="+selectedSportId)
   .then(data=>{
       stockItems = data.data
       setStockTable(data.data)
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


function setStockTable(data){
   var tbody = document.querySelector("#tbody")
   tbody.innerHTML = ""

   data.forEach(item =>{
       var tr = document.createElement("tr")
       $(tr).attr("id",item.itemId)
       tr.innerHTML = `
       <td class="itemName">${item.name}</td><td>${item.quantity}</td><td>${item.borrowedAmount}</td><td>${item.lostAmount}</td>
       <td>${ user == "storekeeper" ? '<button class="btn btn-primary" onclick="stockModal(event)">Stock</button><button class="btn btn-danger ml-4" onclick="editModal(event)">Edit</button>':'' }</td 
       `
       tbody.appendChild(tr)
   })
   $('#itemTable').DataTable()
   $("#sportwrapper").remove()
   $("#tableWrapper").show()
}


function stockModal(e){
   var tr = e.target.parentNode.parentNode
   stockItemId =  tr.id
   document.querySelector("#stockItemName").innerHTML = $(tr).find(".itemName").html()
   $("#stockModal").modal('show')
}

function addStockItems(){
   var addedAmount = document.querySelector("#addedAmount").value
   addedAmount = parseInt(addedAmount)

   if(addedAmount < 1){
      Swal.fire({
         position: 'center',
         type : 'error',
         title : `Amount mus be greater tha 0`,
         showConfirmButton: false,
         timer: 1500
       })
       return
   } 

   var item = stockItems.filter((item)=>{
       return item.itemId == stockItemId
   })[0]

  item.quantity += addedAmount

  axios.put("/items",item)
  .then(data=>{
      Swal.fire({
         position: 'center',
         type : 'success',
         title : `Stocked Item`,
         showConfirmButton: false,
         timer: 1500
      })
     viewStockTable(true)
     $("#stockModal").modal("hide")
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
function editModal(e){
   var tr = e.target.parentNode.parentNode
   stockItemId =  tr.id
   document.querySelector("#editName").value = $(tr).find(".itemName").html()
   var item = stockItems.filter((item)=>{
      return item.itemId == stockItemId
   })[0]
   $("#editSelectSport").val(item.sportId)
   $("#editModal").modal('show')
}


function editStockItem(){
   var name = document.getElementById("editName").value

   if(name == "" || name == undefined){
      Swal.fire({
         position: 'center',
         type : 'error',
         title : `Item name cannot be empty`,
         showConfirmButton: false,
         timer: 1500
      })
      return
   }

   var item = stockItems.filter((item)=>{
      return item.itemId == stockItemId
  })[0]

   item.name = name
   item.sportId = $("#editSelectSport").val()

   axios.put("/items",item)
   .then(data=>{
      Swal.fire({
         position: 'center',
         type : 'success',
         title : `Item edited`,
         showConfirmButton: false,
         timer: 1500
      })
     viewStockTable(true)
     $("#editModal").modal("hide")
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