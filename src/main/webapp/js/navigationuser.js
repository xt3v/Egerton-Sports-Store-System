addScript('js/templates/index/changepassword.js')
addScript('js/templates/index/changedetails.js')
addScript('js/templates/index/checkborrowed.js')
addScript('js/templates/index/checklost.js')
addScript('js/templates/index/requestgame.js')
addScript('js/templates/index/requestItems.js')
//index navigations
function requestGame(){
  addToMain(requestGameTemplate,"Request For Game")
  setupRequestGameTemplate()
} 

function requestItems(){
  addToMain(requestItemTemplate,"Request Items")
  setupRequestItemTemplate()
}

function checkBorrowedItems(){
  addToMain(checkBorrowedItemTemplate,"Borrowed Items")
  setupCheckBorrowedItemTemplate()
}

function checkLostItems(){
   addToMain(checkLostItemTemplate,"Lost Items")
   setupLostItemTemplate()
}

function changeUserDetails(){
  addToMain(changeUserDetailsTemplate,"Change Details")
  setupChangeUserDetailsTemplate()
}

function changeUserPassword(){
    addToMain(changeUserPasswordTemplate,"Change Password")
    setupChangeUserPasswordTemplate()
}



//helper functions
function addScript(path){
    var imported = document.createElement('script');
    imported.src = path;
    document.head.appendChild(imported);
}


function addToMain(template = "",title ="Title"){
    var mainWindow = document.getElementById("modalMain") 
    mainWindow.innerHTML = template
    $("#modalTitle").html(title)
    $("#modal").modal('show')
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }