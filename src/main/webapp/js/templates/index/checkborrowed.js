let checkBorrowedItemTemplate = `
<div id="table-div">
<table id="borrowTable" class="table table-bordered table-stripped">
    <thead class="thead-dark">
       <tr>
         <th>Item Name</th>
         <th>Amount</th>
         <th>Date</th>
       </tr>            
    </thead> 
    <tbody id="tbody">
      
    </tbody>       
</table>
</div>
`

function setupCheckBorrowedItemTemplate(){
    axios.get("/borroweditems?borrowId="+userId)
    .then(data=>{
        addToBorrowedTable(data.data)
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
    var tbody = document.querySelector("#tbody")
    tbody.innerHTML = "" 
    if(data.length == 0){
      
      Swal.fire({
        position: 'center',
        type: 'info',
        title: "No Borrowed Items!",
        showConfirmButton: false,
        timer: 1500
      })
      setTimeout(()=>{
        $("#modal").modal("hide")
       },600)
      return
    } 
    
    data.forEach(item => {
      var content = `<td class="tr-name" >${item.itemName}</td>
                      <td class="tr-qt" >${item.quantity}</td>
                      <td>${item.borrowedDate}</td>
                    `
        var tr = document.createElement("tr")
        tr.innerHTML = content
        tbody.append(tr)                
    });

    $("#borrowTable").DataTable()

}