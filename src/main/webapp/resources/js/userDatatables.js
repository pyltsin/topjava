var ajaxUrl = "ajax/admin/users/";
var datatableApi;

function changeUserEnabled(id, enabled) {
    console.log(id, enabled);
    $.post({
        url: ajaxUrl + "enabled/" + id,
        data: {enabled: enabled},
        success: function () {
            // updateTable();
            reDraw();
            successNoty("Change enabled");
        }
    });
}

function reDraw() {
    $(".userEnabled").each(function () {
        if($(this).is(":checked")) {
            $(this).closest("tr").removeClass("hid");
        }else {
            $(this).closest("tr").addClass("hid");
        }
    })
}

// $(document).ready(function () {
$(function () {

    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
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

    $(".userEnabled").on("click", function () {
        console.log("check");
        changeUserEnabled($(this).closest("tr").attr("id"), $(this).is(":checked"));
    });
    reDraw();

});

function ajaxUrlUpdate() {
    return ajaxUrl;
}