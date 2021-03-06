(function (document, $) {
    "use strict";

    var buttonNeedCheckbox = {
        className: ".cq-dialog-checkbox-showhide-button-link",
        nameCheckbox: "cqDialogCheckboxShowhideButtonLinkTarget",
    };

    $(document).on("foundation-contentloaded", function (e) {
        // if there is already an inital value make sure the according target element becomes visible
        checkboxShowHideHandler($(buttonNeedCheckbox.className, e.target));
    });

    $(document).on("change", buttonNeedCheckbox.className, function () {
        checkboxShowHideHandler($(this));
    });

    function checkboxShowHideHandler(el) {
        el.each(function (i, element) {
            if ($(element).is("coral-checkbox")) {
                // handle Coral3 base drop-down
                Coral.commons.ready(element, function (component) {
                    showHide(component, element);
                    component.on("change", function () {
                        showHide(component, element);
                    });
                });
            } else {
                // handle Coral2 based drop-down
                var component = $(element).data("checkbox");
                if (component) {
                    showHide(component, element);
                }
            }
        });
    }

    function showHide(component, element) {
        // get the selector to find the target elements. its stored as data-.. attribute
        var target = $(element).data(buttonNeedCheckbox.nameCheckbox);
        var $target = $(target);

        if (target) {
            $target.addClass("hide");
            if (component.checked) {
                $target.removeClass("hide"); 
            }
        }
    }
})(document, Granite.$);
  