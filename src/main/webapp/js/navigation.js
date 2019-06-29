addScript("js/templates/storekeeper/addnewitem.js")
addScript("js/templates/registerstudent.js")
addScript("js/templates/storekeeper/clearstudent.js")
addScript("js/templates/storekeeper/returnitems.js")
addScript("js/templates/storekeeper/recoverlostitems.js")
addScript("js/templates/coordinator/registersport.js")
addScript("js/templates/coordinator/registerteam.js")
addScript("js/templates/coordinator/registercoach.js")
addScript("js/templates/coordinator/registerfield.js")
addScript("js/templates/storekeeper/issueitem.js")
addScript("js/templates/storekeeper/selectissueitems.js")
addScript("js/templates/viewstock.js")
addScript("js/templates/viewstudents.js")
addScript("js/templates/coordinator/viewteams.js")
addScript("js/templates/coordinator/viewcoaches.js")
addScript("js/templates/coordinator/viewsports.js")
addScript("js/templates/coordinator/viewfields.js")
addScript("js/templates/coordinator/gamerequests.js")
addScript('js/templates/storekeeper/requesteditems.js')
addScript("js/templates/coordinator/viewgames.js")

//storekeeper views
function clearStudent(){
    addToMain(clearStudentTemplate)
    setupClearStudentTemplate()
}

function recoverLostItems(){
    addToMain(recoverLostItemsTemplate)
    setupRecoverLostItemsTemplate()
}

function addNewItem(){
   addToMain(addNewItemTemplate)
   setupAddNewItemTemplate()
}

function issueItem(){
    addToMain(issueItemTemplate)
    setupIssueItemTemplate()
}
function returnItems(){
   addToMain(returnItemTemplate)
   setupReturnedItemsTemplate()
}

function selectIssueItems(){
    addToMain(selectIssueItemsTemplate)
    setupSelectIssueItemsTemplate()
}

//shared views
function registerStudent(){
    addToMain(registerStudentTemplate)
    setupRegisterStudentTemplate()
}

function viewItems(){
    addToMain(viewStockTemplate)
    setupViewStockTemplate()
}

function viewStudents(){
    addToMain(viewStudentsTemplate)
    setupViewStudentsTemplate()
}
//coordinator views
function registerSport(){
    addToMain(registerSportTemplate)
    setupRegisterSportTemplate()
}

function registerTeam(){
    addToMain(registerTeamTemplate)
    setupRegisterTeamTemplate()
}

function registerCoach(){
    addToMain(registerCoachTemplate)
    setupRegisterCoachTemplate()
}

function registerField(){
    addToMain(registerFieldTemplate)
    setupRegisterFieldTemplate()
}

function viewTeams(){
    addToMain(viewTeamsTemplate)
    setupViewTeamsTemplate()
}

function viewCoaches(){
    addToMain(viewCoachTemplate)
    setupViewCoachTemplate()
}

function viewSports(){
    addToMain(viewSportsTemplate )
    setupViewSportsTemplate()
}

function gameRequests(){
    addToMain(gameRequestTemplate)
    setupGameRequestTemplate()   
}

function viewFields(){
    addToMain(viewFieldsTemplate)
    setupViewFieldsTemplate()
}

function viewgames(){
    addToMain(viewGameTemplate)
    setupViewGameTemplate()
}

function requestedItems(){
   addToMain(requestedItemsTemplate)
   setupRequestItemTemplate()
}

//helper functions
function addScript(path){
    var imported = document.createElement('script');
    imported.src = path;
    document.head.appendChild(imported);
}


function addToMain(template){
    var mainWindow = document.getElementById("main-window") 
    mainWindow.innerHTML = template
}



