let changeUserDetailsTemplate = `
<div class="row justify-content-center">
<div class="col-md-8">
    <form id="changedetailsform" >
        <div class="form-group">
             <label>Name</label>
             <input id="name" id="name" class="form-control" parsley-trigger="change" placeholder="Name" type="text" required="true">
        </div>
        <div class="form-group">
                <label>Phone No</label>
                <input name="phoneNo" id="phoneNo" parsley-trigger="change" 
                parsley-regex-message="Please enter a valid Kenyan mobile phone number."
                class="form-control" placeholder="Phone Number" type="text" required="true" 
                required="true"> 
        </div>
            
        <div class="form-group">
            <label>Residence</label>
            <input name="residence" id="residence" parsley-trigger="change" class="form-control" placeholder="Place of Residence" type="text" required="true">   
        </div> 
       <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</div>
`

function setupChangeUserDetailsTemplate(){
   $("#changedetailsform").parsley()
    
   $("#changedetailsform").on('submit',function(e){
    e.preventDefault()
    var form = $(this)
    
    //TODO regex for reg no
    if (form.parsley().isValid()){
       
        axios.put("/students",{
            name : $("#name").val(),
            regNo :userId,
            residence : $("#residence").val(),
            phoneNo :$("#phoneNo").val()
        })
        .then(data=>{
            $("#modal").modal("hide")
            Swal.fire({
                position: 'center',
                type: 'success',
                title: 'Details Changed',
                showConfirmButton: false,
                timer: 1500
              })

        })
        .catch(err=>{
            console.log(JSON.stringify(err))
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

 setupDetails()
}


function setupDetails(){
    axios.get(`/students?id=${userId}`)
    .then(data=>{
       var res = data.data
       $("#name").val(res.name)
       $("#residence").val(res.residence)
       $("#phoneNo").val(res.phoneNo)
    })
    .catch(err=>{
        console.log(JSON.stringify(err))
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