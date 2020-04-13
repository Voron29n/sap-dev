(function (document, $) {
    "use strict";

    var needComponentTitle = {
        className: ".cq-dialog-checkbox-showhide-columns-title",
        nameCheckbox: "cqDialogCheckboxShowhideColumnsTitleTarget",
    };

    var needButton = {
        className: ".cq-dialog-checkbox-showhide-columns-button",
        nameCheckbox: "cqDialogCheckboxShowhideColumnsButtonTarget",
    };

    var needButtonTitle = {
        className: ".cq-dialog-checkbox-showhide-columns-button-title",
        nameCheckbox: "cqDialogCheckboxShowhideColumnsButtonTitleTarget",
    };

    $(document).on("foundation-contentloaded", function (e) {
        // if there is already an inital value make sure the according target element becomes visible
        checkboxShowHideHandler($(needComponentTitle.className, e.target), needComponentTitle.nameCheckbox);
        checkboxShowHideHandler($(needButton.className, e.target) , needButton.nameCheckbox);
        checkboxShowHideHandler($(needButtonTitle.className, e.target), needButtonTitle.nameCheckbox);
    });

    $(document).on("change", needButton.className, function (e) {
        checkboxShowHideHandler($(this) , needButton.nameCheckbox);
    });

    $(document).on("change", needComponentTitle.className, function (e) {
        checkboxShowHideHandler($(this), needComponentTitle.nameCheckbox);
    });

    $(document).on("change", needButtonTitle.className, function (e) {
        checkboxShowHideHandler($(this), needButtonTitle.nameCheckbox);
    });

    function checkboxShowHideHandler(el , nameCheckbox) {
        el.each(function (i, element) {
            if ($(element).is("coral-checkbox")) {
                // handle Coral3 base drop-down
                Coral.commons.ready(element, function (component) {
                    showHide(component, element, nameCheckbox);
                    component.on("change", function () {
                        showHide(component, element, nameCheckbox);
                    });
                });
            } else {
                // handle Coral2 based drop-down
                var component = $(element).data("checkbox");
                if (component) {
                    showHide(component, element, nameCheckbox);
                }
            }
        });
    }

    function showHide(component, element , nameCheckbox) {
        // get the selector to find the target elements. its stored as data-.. attribute
        var target = $(element).data(nameCheckbox);
        var $target = $(target);

        if (target) {
            $target.addClass("hide");
            if (component.checked) {
                $target.removeClass("hide"); 
            }
        }
    }
})(document, Granite.$);
  