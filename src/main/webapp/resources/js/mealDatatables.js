var ajaxUrl = "rest/profile/meals/";
var datatableApi;
var startDate = "";
var endDate = "";
var startTime = "";
var endTime = "";

function makeFilter() {
    $("#filterForm").submit(function () {
        startDate = $("#startDate").val();
        endDate = $("#endDate").val();
        startTime = $("#startTime").val();
        endTime = $("#endTime").val();
        updateTable();
        return false;
    });
    $("#filterFormReset").click(function () {
        startDate = "";
        endDate = "";
        startTime = "";
        endTime = "";
        $("#filterForm").each(function(){
            this.reset();
        });
        updateTable();
        return false;
    })
}


function reDraw() {

}
// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [

            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
    makeFilter();
});

function paramFilter() {
    return "startTime=" + startTime + "&endTime=" + endTime + "&startDate=" + startDate + "&endDate=" + endDate;
}

function ajaxUrlUpdate() {
    return ajaxUrl + "/filter?" + paramFilter();
}