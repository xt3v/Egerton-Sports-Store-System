let requestGameTemplate = `
<form id="gameForm">
<div class="row justify-content-center">
  <div class="col-md-3">
  <label>Date : </label>
  <input type="text" id="datepicker" class="form-control" data-parsley-date-of data-provide="datepicker" required="true">   
  <small class="form-text text-danger" style="display:none;">Enter valid date !</small>
</div>
<div class="col-md-3">
  <label>Time : </label>
  <input id="timepicker" class="form-control" type="time" min="08:00:00" max="17:00:00" required="true"/> 
</div>

<div class="col-md-3">
  <label>Duration (hours) : </label>
  <input id="duration" class="form-control" type="number" min="1"  required="true"/> 
</div>
</div>
<div class="col-md-12 d-flex justify-content-center mt-1">
   <button class="btn btn-sm btn-primary">Request</button>
</div>
</form>
`

function setupRequestGameTemplate(){
    $('.datepicker').datepicker()
    $("#datepicker+small").hide()

    $("#gameForm").on('submit',function(e){
        e.preventDefault()
        
        var date = new Date()
        var choosenDate = new Date($("#datepicker").val())
        if(choosenDate >= date){
          axios.post('/gamerequest',{
            date : $("#datepicker").val(),
            time : $("#timepicker").val(),
            duration : $("#duration").val() ,
            userId : userId,
          })
           .then(data=>{
            Swal.fire({
              position: 'center',
              type: 'success',
              title: 'Requested for Game',
              showConfirmButton: false,
              timer: 1500
            })
           })
           .catch(err=>{
            let message = err.response.data.message
            Swal.fire({
                position: 'center',
                type: 'error',
                title: message,
                showConfirmButton: false,
                timer: 1500
              })
           }) 
           $("#modal").modal("hide")
        }else{
          $("#datepicker+small").show()
        }
          
  })

}