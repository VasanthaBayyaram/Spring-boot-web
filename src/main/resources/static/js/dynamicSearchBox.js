$(document).ready(function() {
    //attach autocomplete
    $("#searchCompany").autocomplete({
        minLength: 1,
        delay: 500,
        //define callback to format results
        source: function (request, response) {
	alert('script')
            $.getJSON("/investor/get_company_list", request, function(result) {
                response($.map(result, function(item) {
                    return {
                        // following property gets displayed in drop down
                        label: item.name,
                        // following property gets entered in the textbox
                        value: item.name,
                        // following property is added for our own use
                        tag_url: "http://" + window.location.host + "/tags/" + item.tagId + "/" + item.name
                    }
                }));
            });
        },

        //define select handler
        select : function(event, ui) {
            if (ui.item) {
                event.preventDefault();
                $("#selected_tags span").append('<a href=" + ui.item.tag_url + " target="_blank">'+ ui.item.label +'</a>');
                //$("#tagQuery").value = $("#tagQuery").defaultValue
                var defValue = $("#tagQuery").prop('defaultValue');
                $("#tagQuery").val(defValue);
                $("#tagQuery").blur();
                return false;
            }
        }
    });
});
