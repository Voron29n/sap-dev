(function (document, $) {
    "use strict";

    var onlyVideoCheckbox = {
        className: ".cq-dialog-checkbox-showhide-only-video",
        nameCheckbox: "cqDialogCheckboxShowhideVideoDataTarget",
    };

    $(document).on("foundation-contentloaded", function (e) {
        // if there is already an inital value make sure the according target element becomes visible
        checkboxShowHideHandler($(onlyVideoCheckbox.className, e.target));
    });

    $(document).on("change", onlyVideoCheckbox.className, function (e) {
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
        console.log("showing");
        // get the selector to find the target elements. its stored as data-.. attribute
        var target = $(element).data(onlyVideoCheckbox.nameCheckbox);
        var $target = $(target);

        if (target) {
            let coralTabId =
                "#" + $target.parent().parent().attr("aria-labelledby");
            $(coralTabId).addClass("hide");
            $target.addClass("hide");
            if (!component.checked) {
                $target.removeClass("hide");
                $(coralTabId).removeClass("hide");
            }
        }
    }
})(document, Granite.$);
