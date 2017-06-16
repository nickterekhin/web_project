/**
 * Created by Nick on 15.06.17.
 */

(function($){

    $.fn.ntValidator = function(options)
    {
        var opt = $.extend({
            rules:{}
        },options);

        var validator = new ValidatorService(opt,this);

        this.submit(function(event){
            return validator.form();
        });
    };

    var ValidatorService = function(options,form)
    {
        var _self = this;
        this.currentForm = form;
        this.errorList = [];
        this.errorMap = [];
        this.currentElements = $([]);

        this.settings = $.extend({
            rules:{},
            ignore:":hidden",
            messages:{},
            errorClass:"error",
            validClass:"valid",
            errorElement:"label",
            onfocusout:function(el){
                if(el.name in _self.errorMap || !optional(el))
                {
                   _self.checkElement(el);
                }

            },
            onkeyup:function(el,event){


                if(event.which==9)
                return;
               _self.checkElement( el );
            },
            highlight:function( element, errorClass, validClass ) {
                $( element ).addClass( errorClass ).removeClass( validClass );
            },
            unhighlight:function(element, errorClass, validClass)
            {
                $( element ).removeClass( errorClass ).addClass( validClass );
            }

        },options);

        this.default_messages= {
            required: "This field is required.",
            number: "Please enter a valid number."
        };

        this.check_methods = {
            required: function (el) {
                return $.trim( el.val() ).length > 0;
            },
            number: function (el) {
                return optional( el.val() ) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test( el.val() );
            }
        };


        var optional = function(el)
        {
            return _self.check_methods.required.call(_self,$(el));
        };
        this.isValid = function()
        {
            return this.errorList.length === 0;
        };
        this.reset= function() {
            this.errorList = [];
            this.currentElements = $( [] );
        };
        this.validElements = function() {
            return this.currentElements.not( this.invalidElements() );
        };

        this.invalidElements = function() {
            return $( this.errorList ).map(function() {
                return this.element;
            });
        };

        this.init();

    };
    ValidatorService.prototype.init = function()
    {
        this.reset();
        this.initEventHandler(":text, [type='number']",
            "focusin focusout keyup");

    };
    ValidatorService.prototype.initEventHandler = function(delegate,type)
    {
        var _self = this;
        $(this.currentForm).bind(type,function(event){
            var target = $(event.target);
            if ( target.is(delegate) ) {
                var eventType = "on" + event.type;

                if ( _self.settings[ eventType ]) {

                    _self.settings[ eventType ].call( _self, target[0], event );
                }
            }
        });
    };
    ValidatorService.prototype.form = function()
    {
        this.reset();
        for ( var i = 0, elements = (this.currentElements = this.getFormElements()); elements[ i ]; i++ ) {
            console.log(this.currentElements);
            this.check( elements[ i ] );
        }
        this.showErrors();
        return this.isValid();
    };

    ValidatorService.prototype.check = function(el)
    {

        var rules=this.getRules(el),method,rule,result;

        for(method in rules)
        {
            rule = {method: method, params: rules[method]};
            result = this.check_methods[ method ].call( this, $(el));
            if(!result)
            {
                this.addErrorToList(el,rule);
                return false;
            }
        }
        return true;
    };
    ValidatorService.prototype.getRules = function(el)
    {
        var rules;
        if ( this.settings.rules ) {
            rules =  this.settings.rules[ el.name ] || {};
        }
        return rules;
    };
    ValidatorService.prototype.getFormElements = function()
    {
        var validator = this,
            rulesCache = {};

        return $( this.currentForm )
            .find( "input, select, textarea" )
            .not( ":submit, :reset, :image, [disabled], [readonly]" )
            .not( this.settings.ignore )
            .filter( function() {
                if ( this.name in rulesCache || validator.settings.rules[this.name] === undefined) {
                    return false;
                }
                rulesCache[ this.name ] = true;
                return true;
            });
    };

    ValidatorService.prototype.addErrorToList = function(el,rule)
    {
        var message = this.getErrorMessage(el.name,rule.method);

        this.errorList.push({
            message:message,
            element:el,
            method:rule.method
        });

        this.errorMap[el.name] = message;
    };

    ValidatorService.prototype.getErrorMessage = function(name,method)
    {
        var message = this.settings.messages[name];
        message =  message && message[method];

        if(!message)
            message = this.default_messages[method];

        return message;
    };

    ValidatorService.prototype.showErrors = function()
    {
        var  error,elements;
        for ( i = 0; this.errorList[ i ]; i++ ) {
            error = this.errorList[ i ];
            if ( this.settings.highlight ) {
                this.settings.highlight.call( this, error.element, this.settings.errorClass, this.settings.validClass );
            }
            this.showLabel( error.element, error.message );
        }
        /*if ( this.settings.unhighlight ) {
            for ( i = 0, elements = this.validElements(); elements[ i ]; i++ ) {
                this.settings.unhighlight.call( this, elements[ i ], this.settings.errorClass, this.settings.validClass );
            }
        }*/
    };

    ValidatorService.prototype.showLabel=function(el,message)
    {
        var error,
            errorElement = this.elementErrorLabel(el);

        if(errorElement.size()>0)
        {
            errorElement.removeClass( this.settings.validClass ).addClass( this.settings.errorClass );
            errorElement.html( message );

        }else {
            error = $("<" + this.settings.errorElement + ">").attr("id", el.name + "-error").addClass(this.settings.errorClass).html(message || "");

            error.insertAfter(el);

            if (error.is("label")) {
                error.attr("for", el.name);
            }
        }

    };

    ValidatorService.prototype.elementErrorLabel = function(el)
    {
        var errorClass = this.settings.errorClass.split( " " ).join( "." );
        return $( this.settings.errorElement + "." + errorClass, $(this.currentForm)).filter(this.settings.errorElement+"[id='" + el.name + "-error'], "+this.settings.errorElement+"[id='" + el.name + "-error'] *");
    };

    ValidatorService.prototype.checkElement = function(el)
    {
        var result;
        this.reset();
        result = this.check(el);

        if(result) {
            this.removeErrors(el);
            this.settings.unhighlight.call( this, el, this.settings.errorClass, this.settings.validClass );
        }
        this.showErrors();
        return result;
    };
    ValidatorService.prototype.removeErrors = function(el)
    {
        var _self = this;

        $("#"+el.name+"-error").remove();

        delete this.errorMap[el.name];
        $.each(this.errorList, function(i){
            if(this.element != undefined && this.element.name == el.name) {
                _self.errorList = _self.errorList.splice(i,1);
                return true;
            }
        });


    }
})(jQuery);