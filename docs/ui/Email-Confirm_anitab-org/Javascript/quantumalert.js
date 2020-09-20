var scripttag = document.createElement('link');
scripttag.rel = "stylesheet";
scripttag.href = "quantumalert.css";
document.head.appendChild(scripttag);

var succ = "https://cdn.jsdelivr.net/gh/cosmogicofficial/quantumalert/image/success.svg";
new Image().src = succ;
var err = "https://cdn.jsdelivr.net/gh/cosmogicofficial/quantumalert/image/error.svg";
new Image().src = err;
var inf = "https://cdn.jsdelivr.net/gh/cosmogicofficial/quantumalert/image/info.svg";
new Image().src = inf;
var war = "https://cdn.jsdelivr.net/gh/cosmogicofficial/quantumalert/image/warning.svg";
new Image().src = war;

var alertcontainer;
var popupdiv;
var popupcontent;
var errortext;
var pop_heading;
var no_btn_fun = "write_function_name_here";
var yes_btn_fun = "write_function_name_here";
var type_field;
var matches = document.getElementsByClassName('alertcontainer');

function rmx() {
  document.body.removeChild(matches.item(0));
}

function close_qual() {
  setTimeout(function() {
    while (matches.length > 0) {
      rmx();
    }
  }, 250);
}

function checker() {
  alertcontainer = document.createElement('div');
  alertcontainer.className = 'alertcontainer';
  document.body.appendChild(alertcontainer);
  popupdiv = document.createElement('div');
  popupdiv.id = 'popupdiv';
  popupdiv.innerHTML = '<span id="closepopup" onclick="close_qual();" ><svg viewbox="0 0 40 40"  id="close-x" fill="#000"><path d="M 10,10 L 30,30 M 30,10 L 10,30" /></svg></span>';
  alertcontainer.appendChild(popupdiv);
  var icon = war;
}

function structure() {
  if (alertcontainer == undefined) {
    checker();
  } else {
    //To remove the previous created  alertcontainer div
    if (matches.length > 0) {
      rmx();
    }
    checker();
  }
}

function pop_simple_content(pop_simple) {
  pop_simple_structure = document.createElement('p');
  pop_simple_structure.id = 'pop_simple_structure';
  pop_simple_structure.textContent = pop_simple;
  popupdiv.appendChild(pop_simple_structure);
}

function errorcontent() {
  errortext = document.createElement('div')
  errortext.id = 'errortext';
  errortext.innerHTML = ' <img id="errorimage" src="' + err + '"  /><br>';
  popupdiv.appendChild(errortext);
}

function write_function_name_here() {
  console.log("Define function name for buttons");
}

function alert_btn(yes_btn, no_btn, yes_btn_fun, no_btn_fun) {
  if (yes_btn_fun === undefined || no_btn_fun === undefined) {
    no_btn_fun = "write_function_name_here";
    yes_btn_fun = "write_function_name_here";
    alert_btn_struct = document.createElement('div')
    alert_btn_struct.id = 'alert_btn_struct';
    alert_btn_struct.innerHTML = ' <span id="btn-no" onclick="' + no_btn_fun + '();">' + no_btn + '  </span>&nbsp <span id="btn-yes" onclick="' + yes_btn_fun + '();">' + yes_btn + '  </span>';
    popupdiv.appendChild(alert_btn_struct);
  } else {
    alert_btn_struct = document.createElement('div')
    alert_btn_struct.id = 'alert_btn_struct';
    alert_btn_struct.innerHTML = ' <span id="btn-no" onclick="' + no_btn_fun + '();">' + no_btn + '  </span>&nbsp <span id="btn-yes" onclick="' + yes_btn_fun + '();">' + yes_btn + '  </span>';
    popupdiv.appendChild(alert_btn_struct);
  }

}

function heading(pop_heading_value) {
  pop_heading = document.createElement('p');
  pop_heading.id = 'pop_heading';
  pop_heading.textContent = pop_heading_value;
  popupdiv.appendChild(pop_heading);
}

function pop_content_head(pop_heading_content) {
  pop_head_content = document.createElement('p');
  pop_head_content.id = 'pop_head_content';
  pop_head_content.textContent = pop_heading_content;
  popupdiv.appendChild(pop_head_content);
}
var input_element, inx;
var place_holder;

function input_field(type_field, place_holder) {
  if (place_holder === undefined) {
    place_holder = "Enter Text here";
  }
  input_element = document.createElement("input");
  input_element.id = 'input_element';
  input_element.setAttribute("type", type_field);
  input_element.setAttribute("placeholder", place_holder);
  popupdiv.appendChild(input_element);
  popupdiv.insertBefore(input_element, alert_btn_struct); // Insert <li> before the first child of <ul>
  inx = input_element.value;
  input_element.onchange = function(e) {
    inx = e.target.value;
  }
}

function HeadingwithText(pop_heading_value, pop_heading_content) {
  structure();
  heading(pop_heading_value);
  pop_heading.style.marginTop = "50px";
  pop_heading.style.fontSize = "1.8rem";
  pop_content_head(pop_heading_content);
}

function whitelayout() {
  pop_heading.style.color = "#000";
  pop_head_content.style.color = "#000";
}
function darkLayout() {
  popupdiv.style.background = "#1c1c1c";
  popupdiv.style.color = "#fff";
  document.getElementById('close-x').style.stroke = "#fff";
  pop_heading.style.color = "#fff";
  pop_head_content.style.color = "#fff";

}

function darkBlueLayout() {
  popupdiv.style.background = "#001b33";
  popupdiv.style.color = "#fff";
  document.getElementById('close-x').style.stroke = "#ffffff";
  pop_heading.style.color = "#fff";
  pop_head_content.style.color = "#fff";
}

function alertWithIcons(pop_heading_value, pop_heading_content) {
  structure();
  errorcontent();
  heading(pop_heading_value);
  pop_content_head(pop_heading_content);
}

function confirmValidation(icon, yes_btn, no_btn, yes_btn_fun, no_btn_fun, type_field, place_holder) {
  popupdiv.style.paddingBottom = "0px";
  if (icon === undefined) {
    icon = war;
    errortext.innerHTML = ' <img id="errorimage" src="' + icon + '"  /><br>';
  } else {
    errortext.innerHTML = ' <img id="errorimage" src="' + icon + '"  /><br>';
  }

  (yes_btn === undefined) ? yes_btn = "Ok": yes_btn = yes_btn;
  (no_btn === undefined) ? no_btn = "Cancel": no_btn = no_btn;
  alert_btn(yes_btn, no_btn, yes_btn_fun, no_btn_fun);
  if (type_field === undefined) {
    console.log("Input field not used");
  } else {
    input_field(type_field, place_holder);
    input_element.style.color = "#000";
  }
}
class notify {
  // For the simple popup alert
  sw(pop_simple) {
    structure();
    pop_simple_content(pop_simple);
    popupdiv.style.paddingBottom = "0px";
pop_simple_structure.style.color = "#000";
  }
  //For simple dark background alert
  sd(pop_simple) {
    structure();
    pop_simple_content(pop_simple);
    pop_simple_structure.style.color = "#fff";
    popupdiv.style.paddingBottom = "0px";
    darkLayout();
  }
  //For simple dark blue background alert
  sdb(pop_simple) {
    structure();
    pop_simple_content(pop_simple);
    popupdiv.style.paddingBottom = "0px";
    pop_simple_structure.style.color = "#fff";
    darkBlueLayout();
  }

  // For simple  white with heading
  swh(pop_heading_value, pop_heading_content) {
    HeadingwithText(pop_heading_value, pop_heading_content);
  whitelayout();
  }

  // For simple dark with heading
  sdh(pop_heading_value, pop_heading_content) {
    HeadingwithText(pop_heading_value, pop_heading_content);
    darkLayout();
  }

  // For simple dark with heading
  sdbh(pop_heading_value, pop_heading_content) {
    HeadingwithText(pop_heading_value, pop_heading_content);
    darkBlueLayout();
  }

  // For error message
  error(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    whitelayout();
  }

  // For error with dark background message
  errord(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    darkLayout();
  }

  // For error with dark blue background message
  errordb(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    darkBlueLayout();
  }
  // For success message
  success(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + succ + '" /><br>';
    whitelayout();

  }
  // For success with dark background message
  successd(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + succ + '" /><br>';
    darkLayout();
  }
  // For success  with dark blue message
  successdb(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + succ + '" /><br>';
    darkBlueLayout();
  }
  // For warning message
  warning(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + war + '"  /><br>';
    whitelayout();

  }
  // For warning message with dark background
  warningd(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + war + '"  /><br>';
    darkLayout();
  }
  // For warning message
  warningdb(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + war + '"  /><br>';
    darkBlueLayout();
  }
  // For info message
  info(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + inf + '"  /><br>';
    whitelayout();

  }
  // For info message with dark background
  infod(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + inf + '"  /><br>';
    darkLayout();
  }
  // For info message with dark background
  infodb(pop_heading_value, pop_heading_content) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + inf + '"  /><br>';
    darkBlueLayout();
  }
  //For  icon with white background
  icon(pop_heading_value, pop_heading_content, icon) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + icon + '"  /><br>';
    whitelayout();
  }
  //For  icon with dark background
  icond(pop_heading_value, pop_heading_content, icon) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + icon + '"  /><br>';
    darkLayout();
  }
  //For  icon with dakrblue background
  icondb(pop_heading_value, pop_heading_content, icon) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    errortext.innerHTML = ' <img id="errorimage" src="' + icon + '"  /><br>';
    darkBlueLayout();
  }
  //For confirm Modal with with white background buttons and actions
  confirm(pop_heading_value, pop_heading_content, icon, yes_btn, no_btn, yes_btn_fun, no_btn_fun, type_field, place_holder) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    confirmValidation(icon, yes_btn, no_btn, yes_btn_fun, no_btn_fun, type_field, place_holder);
    whitelayout();
  }
  //For confirm Modal with with white background buttons and actions
  confirmd(pop_heading_value, pop_heading_content, icon, yes_btn, no_btn, yes_btn_fun, no_btn_fun, type_field, place_holder) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    confirmValidation(icon, yes_btn, no_btn, yes_btn_fun, no_btn_fun, type_field, place_holder);
    darkLayout();
    document.getElementById('btn-no').style.color = "#fff";
    }
  //For confirm Modal with with white background buttons and actions
  confirmdb(pop_heading_value, pop_heading_content, icon, yes_btn, no_btn, yes_btn_fun, no_btn_fun, type_field, place_holder) {
    alertWithIcons(pop_heading_value, pop_heading_content);
    confirmValidation(icon, yes_btn, no_btn, yes_btn_fun, no_btn_fun, type_field, place_holder);
    darkBlueLayout();
    document.getElementById('btn-no').style.color = "#fff";
  }

}

var Qual = new notify();
