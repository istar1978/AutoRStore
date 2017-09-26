//bottom menu Infomation.

var tree_isIE = navigator.appName.indexOf("Netscape") == -1;                                                                    
	var tree_isNN = (! tree_isIE) && (parseInt(navigator.appVersion.charAt(0)) < 5);                                                                    
	var tree_isN6 = (! tree_isIE) && (! tree_isNN);                                                                    
	var tree_BeginPoint;                                                                    
	var tree_LayerBox = new Array();                                                                    
	var tree_Visible = new Object();                                                                    
	var tree_Index = new Object();                                                                    
	var tree_Level = new Object();                                                                    
	var tree_CrossImg = new Object();                                                                    
	var tree_line_img_group = new Object();                                                                    
	var tree_line_img = new Object();                                                                    
	var tree_selected_id = null;                                                                    
	var tree_highlightColor = document.tree_event.highlight_color.value;                                                                    
	var tree_TRUE = (new Boolean(true)).toString();                                                                    
	var tree_FALSE = (new Boolean(false)).toString();                                                                    
                                                                    
	function tree_initialize(){                                                                    
		var idx, lay, lay_id, lineCG, no = 0;                                                                    
		var tree_IMG = new Object();                                                                    
			tree_IMG.T = new Object();                                                                    
			tree_IMG.T[tree_FALSE] = "images/tree/conct_pT.gif";                                                                    
			tree_IMG.T[tree_TRUE] = "images/tree/conct_mT.gif";                                                                    
			tree_IMG.L = new Object();                                                                    
			tree_IMG.L[tree_FALSE] = "images/tree/conct_pL.gif";                                                                    
			tree_IMG.L[tree_TRUE] = "images/tree/conct_mL.gif";                                                                    
		tree_Visible.tree_root = true;                                                          
		if(tree_isNN){                                                                    
			tree_BeginPoint = tree_getLayerObject("tree_top").pageY;                                                                    
			var tree_bottom = tree_BeginPoint;                                                                    
			var div_tag = document.layers;                                                                    
			var lay_height = tree_BeginPoint;                                                                    
			for(idx = 0; idx < div_tag.length; idx++){                                                                    
				lay = div_tag[idx];                                                                    
				lay_id = lay.id;                                                                    
				if(lay_id.indexOf("tree_root") == 0){                                                                    
					tree_bottom += lay.clip.height;                                                                    
					if(lay_id != "tree_root"){                                                                    
						tree_LayerBox[no] = lay;                                                                    
						tree_Index[lay_id] = no;                                                                    
						tree_Level[lay_id] = lay_id.substring(0, lay_id.lastIndexOf("/"));                                                                    
						lineCG = tree_IMG[lay_id.charAt(lay_id.length - 1)];                                                                    
						if(lineCG != null){                                                                    
							tree_Visible[lay_id] = false;                                                                    
							tree_line_img_group[lay_id] = lineCG;                                                                    
							tree_line_img[lay_id] = lineCG[tree_FALSE];                                                                    
							tree_CrossImg[lay_id] = lay.document.images[lay.document.images.length - 2];                                                                    
							tree_CrossImg[lay_id].src = lineCG[tree_FALSE];                                                                    
						}                                                                    
						if(tree_Level[lay_id] == "tree_root"){                                                                    
							lay.top = lay_height;                                                                    
							lay_height += lay.clip.height;                                                                    
							lay.hidden = false;                                                                    
						}                                                                    
					}                                                                    
					else{                                                                    
						tree_LayerBox[no] = lay;                                                                    
						tree_Index[lay_id] = no;                                                                    
						tree_Level[lay_id] = lay_id;                                                                    
						lay.top = lay_height;                                                                    
						lay_height += lay.clip.height;                                                                    
						lay.hidden = false;                                                                    
					}                                                                    
					no++;                                                                    
				}                                                                    
			}                                                                    
			tree_getLayerObject("tree_bottom").top = tree_bottom;                                                                    
			if(tree_highlightColor == ""){ tree_highlightColor = null; }                                                                    
			window.onresize = tree_repaint;                                                                    
		}                                                                    
		if(tree_isIE){                                                                    
			var div_tag = document.all.tags("DIV");                                                                    
			for(idx = 0; idx < div_tag.length; idx++){                                                                    
				lay = div_tag[idx];                                                                    
				lay_id = lay.id;                                                                    
				if(lay_id.indexOf("tree_root") == 0){                                                                    
					lay.style.textDecoration = "none";                                                                    
					if(lay_id != "tree_root"){                                                                    
						tree_LayerBox[no] = lay;                                                                    
						tree_Index[lay_id] = no;                                                                    
						tree_Level[lay_id] = lay_id.substring(0, lay_id.lastIndexOf("/"));                                                                    
						tree_Visible[lay_id] = false;                                                                    
						lineCG = tree_IMG[lay_id.charAt(lay_id.length - 1)];                                                                    
						if(lineCG != null){                                                                    
							tree_Visible[lay_id] = false;                                                                    
							tree_line_img_group[lay_id] = lineCG;                                                                    
							tree_line_img[lay_id] = lineCG[tree_FALSE];                                                                    
							tree_CrossImg[lay_id] = lay.all.tags("IMG")[lay.all.tags("IMG").length - 2];                                                                    
							tree_CrossImg[lay_id].src = lineCG[tree_FALSE];                                                                    
						}                                                                    
						if(! tree_Visible[tree_Level[lay_id]]){                                                                    
							lay.style.display = "none";                                                                    
						}                                                                    
					}                                                                    
					else{                                                                    
						tree_LayerBox[no] = lay;                                                                    
						tree_Index[lay_id] = no;                                                                    
						tree_Level[lay_id] = lay_id;                                                                    
					}                                                                    
					no++;                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
		if(tree_isN6){                                                                    
			var div_tag = document.getElementsByTagName("DIV");                                                                    
			for(idx = 0; idx < div_tag.length; idx++){                                                                    
				lay = div_tag[idx];                                                                    
				lay_id = lay.id;                                                                    
				if(lay_id.indexOf("tree_root") == 0){                                                                    
					lay.style.textDecoration = "none";                                                                    
					if(lay_id != "tree_root"){                                                                    
						tree_LayerBox[no] = lay;                                                                    
						tree_Index[lay_id] = no;                                                                    
						tree_Level[lay_id] = lay_id.substring(0, lay_id.lastIndexOf("/"));                                                                    
						tree_Visible[lay_id] = false;                                                                    
						lineCG = tree_IMG[lay_id.charAt(lay_id.length - 1)];                                                                    
						if(lineCG != null){                                                                    
							tree_Visible[lay_id] = false;                                                                    
							tree_line_img_group[lay_id] = lineCG;                                                                    
							tree_line_img[lay_id] = lineCG[tree_FALSE];                                                                    
							tree_CrossImg[lay_id] = lay.getElementsByTagName("IMG")[lay.getElementsByTagName("IMG").length - 2];                                                                    
							tree_CrossImg[lay_id].src = lineCG[tree_FALSE];                                                                    
						}                                                                    
						if(! tree_Visible[tree_Level[lay_id]]){                                                                    
							lay.style.display = "none";                                                                    
						}                                                                    
					}                                                                    
					else{                                                                    
						tree_LayerBox[no] = lay;                                                                    
						tree_Index[lay_id] = no;                                                                    
						tree_Level[lay_id] = lay_id;                                                                    
					}                                                                    
					no++;                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
	}                                                                    
	function tree_repaint(){                                                                    
		var lay, idx, lay_height = tree_BeginPoint;                                                                    
		for(idx = 0; idx < tree_LayerBox.length; idx++){                                                                    
			lay = tree_LayerBox[idx];                                                                    
			lay.bgColor = null;                                                                    
			if(tree_Visible[tree_Level[lay.id]]){                                                                    
				lay.top = lay_height;                                                                    
				lay_height += lay.clip.height;                                                                    
				if(tree_line_img[lay.id] != null){                                                                    
					tree_CrossImg[lay.id].src = tree_line_img[lay.id];                                                                    
					if(! tree_Visible[lay.id]){                                                                    
						for(; idx < tree_LayerBox.length; idx++){                                                                    
							if(tree_LayerBox[idx].id.indexOf(lay.id) < 0){                                                                    
								idx--;                                                                    
								break;                                                                    
							}                                                                    
						}                                                                    
					}                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
		if(tree_Index[tree_selected_id] != null){                                                                    
			tree_LayerBox[tree_Index[tree_selected_id]].bgColor = tree_highlightColor;                                                                    
		}                                                                    
	}                                                                    
	function tree_highlight(selected_id){                                                                    
		var lay = tree_LayerBox[tree_Index[tree_selected_id]];                                                                    
		tree_selected_id = selected_id;                                                                    
		if(tree_isIE || tree_isN6){                                                                    
			if(lay != null){                                                                    
				lay.style.borderWidth = "0pt";                                                                    
				lay.style.borderColor = "";                                                                    
				lay.style.backgroundColor = "";                                                                    
			}                                                                    
			if(tree_Index[selected_id] != null){                                                                    
				lay = tree_LayerBox[tree_Index[selected_id]];                                                                    
				lay.style.borderTopWidth = "medium";                                                                    
				lay.style.borderRightWidth = "thick";                                                                    
				lay.style.borderBottomWidth = "medium";                                                                    
				lay.style.borderLeftWidth = "thick";                                                                    
				//lay.style.borderStyle = "ridge";                                                                    
				//lay.style.borderColor = tree_highlightColor;                                                                    
				//lay.style.backgroundColor = tree_highlightColor;                                                                    
			}                                                                    
		}                                                                    
		if(tree_isNN){                                                                    
			if(lay != null){ lay.bgColor = null; }                                                                    
			if(tree_Index[selected_id] != null){                                                                    
				tree_LayerBox[tree_Index[selected_id]].bgColor = tree_highlightColor;                                                                    
			}                                                                    
		}                                                                    
	}                                                                    
	function tree_getLayerObject(lay_id){                                                                    
		if(tree_isNN){ return document.layers[lay_id]; }                                                                    
		if(tree_isIE){ return document.all(lay_id); }                                                                    
		if(tree_isN6){ return document.getElementById(lay_id); }                                                                    
	}                                                                    
	function tree_switch_home(){                                                                    
		var idx;                                                                    
		for(idx in tree_Visible){ tree_Visible[idx] = idx == "tree_root"; }                                                                    
		for(idx in tree_line_img){                                                                    
			tree_line_img[idx] = tree_line_img_group[idx][tree_FALSE];                                                                    
			tree_CrossImg[idx].src = tree_line_img[idx];                                                                    
		}                                                                    
                                                                    
		if(tree_isNN){                                                                    
			var lay_height = tree_BeginPoint;                                                                    
			for(idx in tree_LayerBox){                                                                    
				if(tree_Level[tree_LayerBox[idx].id] == "tree_root"){                                                                    
					tree_LayerBox[idx].hidden = false;                                                                    
					tree_LayerBox[idx].top = lay_height;                                                                    
					lay_height += tree_LayerBox[idx].clip.height;                                                                    
					if(tree_line_img[tree_LayerBox[idx].id] != null){                                                                    
						tree_CrossImg[tree_LayerBox[idx].id].src = tree_line_img[tree_LayerBox[idx].id];                                                                    
					}                                                                    
				}                                                                    
				else{                                                                    
					tree_LayerBox[idx].hidden = true;                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
		if(tree_isIE){                                                                    
			for(idx in tree_LayerBox){                                                                    
				if(tree_Level[tree_LayerBox[idx].id] == "tree_root"){                                                                    
					if(tree_line_img[tree_LayerBox[idx].id] != null){                                                                    
						tree_CrossImg[tree_LayerBox[idx].id].src = tree_line_img[tree_LayerBox[idx].id];                                                                    
					}                                                                    
				}                                                                    
				else{                                                                    
					tree_LayerBox[idx].style.display = "none";                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
		if(tree_isN6){                                                                    
			for(idx in tree_LayerBox){                                                                    
				if(tree_Level[tree_LayerBox[idx].id] == "tree_root"){                                                                    
					if(tree_line_img[tree_LayerBox[idx].id] != null){                                                                    
						tree_CrossImg[tree_LayerBox[idx].id].src = tree_line_img[tree_LayerBox[idx].id];                                                                    
					}                                                                    
				}                                                                    
				else{                                                                    
					tree_LayerBox[idx].style.display = "none";                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
	}                                                                    
	function tree_click_home(){                                                                    
		if((typeof tree_HomeAction) == "function"){                                                                    
			tree_highlight("tree_root");                                                                    
			tree_HomeAction();                                                                    
		}                                                                    
		else{                                                                    
			tree_switch_home();                                                                    
		}                                                                    
	}                                                                    
	function tree_switch_folder(lay_id){                                                                    
		var idx = tree_Index[lay_id];                                                                    
		var lay = tree_LayerBox[idx];                                                                    
		if(tree_Visible[lay_id]){                                                                    
			tree_Visible[lay_id] = false;                                                                    
			tree_line_img[lay_id] = tree_line_img_group[lay_id][tree_FALSE];                                                                    
			if(tree_isNN){                                                                    
				tree_CrossImg[lay_id].src = tree_line_img[lay_id];                                                                    
				var lay_height = lay.top + lay.clip.height;                                                                    
				for(idx++; idx < tree_LayerBox.length; idx++){                                                                    
					if(tree_LayerBox[idx].id.indexOf(lay_id) == 0){                                                                    
						tree_LayerBox[idx].hidden = true;                                                                    
					}                                                                    
					else{                                                                    
						break;                                                                    
					}                                                                    
				}                                                                    
				for(; idx < tree_LayerBox.length; idx++){                                                                    
					if(! tree_LayerBox[idx].hidden){                                                                    
						tree_LayerBox[idx].top = lay_height;                                                                    
						lay_height += tree_LayerBox[idx].clip.height;                                                                    
					}                                                                    
				}                                                                    
			}                                                                    
			if(tree_isIE){                                                                    
				tree_CrossImg[lay_id].src = tree_line_img[lay_id];                                                                    
				for(idx++; idx < tree_LayerBox.length; idx++){                                                                    
					if(tree_LayerBox[idx].id.indexOf(lay_id) == 0){                                                                    
						tree_LayerBox[idx].style.display = "none";                                                                    
					}                                                                    
					else{                                                                    
						break;                                                                    
					}                                                                    
				}                                                                    
			}                                                                    
			if(tree_isN6){                                                                    
				tree_CrossImg[lay_id].src = tree_line_img[lay_id];                                                                    
				for(idx++; idx < tree_LayerBox.length; idx++){                                                                    
					if(tree_LayerBox[idx].id.indexOf(lay_id) == 0){                                                                    
						tree_LayerBox[idx].style.display = "none";                                                                    
					}                                                                    
					else{                                                                    
						break;                                                                    
					}                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
		else{                                                                    
			tree_Visible[lay_id] = true;                                                                    
			tree_line_img[lay_id] = tree_line_img_group[lay_id][tree_TRUE];                                                                    
			if(tree_isNN){                                                                    
				tree_CrossImg[lay_id].src = tree_line_img[lay_id];                                                                    
				var lay_height = lay.top + lay.clip.height;                                                                    
				for(idx++; idx < tree_LayerBox.length; idx++){                                                                    
					lay = tree_LayerBox[idx];                                                                    
					if(lay.id.indexOf(lay_id) == 0){                                                                    
						lay.top = lay_height;                                                                    
						lay_height += lay.clip.height;                                                                    
						lay.hidden = false;                                                                    
						if(tree_line_img[lay.id] != null){                                                                    
							tree_CrossImg[lay.id].src = tree_line_img[lay.id];                                                                    
							if(! tree_Visible[lay.id]){                                                                    
								for(; idx < tree_LayerBox.length; idx++){                                                                    
									if(tree_LayerBox[idx].id.indexOf(lay.id) < 0){                                                                    
										idx--;                                                                    
										break;                                                                    
									}                                                                    
								}                                                                    
							}                                                                    
						}                                                                    
					}                                                                    
					else{                                                                    
						break;                                                                    
					}                                                                    
				}                                                                    
				for(; idx < tree_LayerBox.length; idx++){                                                                    
					if(! tree_LayerBox[idx].hidden){                                                                    
						tree_LayerBox[idx].top = lay_height;                                                                    
						lay_height += tree_LayerBox[idx].clip.height;                                                                    
					}                                                                    
				}                                                                    
			}                                                                    
			if(tree_isIE){                                                                    
				tree_CrossImg[lay_id].src = tree_line_img[lay_id];                                                                    
				for(idx++; idx < tree_LayerBox.length; idx++){                                                                    
					lay = tree_LayerBox[idx];                                                                    
					if(lay.id.indexOf(lay_id) == 0){                                                                    
						lay.style.display = "block";                                                                    
						if(! tree_Visible[lay.id]){                                                                    
							for(; idx < tree_LayerBox.length; idx++){                                                                    
								if(tree_LayerBox[idx].id.indexOf(lay.id) < 0){                                                                    
									idx--;                                                                    
									break;                                                                    
								}                                                                    
							}                                                                    
						}                                                                    
					}                                                                    
					else{                                                                    
						break;                                                                    
					}                                                                    
				}                                                                    
			}                                                                    
			if(tree_isN6){                                                                    
				tree_CrossImg[lay_id].src = tree_line_img[lay_id];                                                                    
				for(idx++; idx < tree_LayerBox.length; idx++){                                                                    
					lay = tree_LayerBox[idx];                                                                    
					if(lay.id.indexOf(lay_id) == 0){                                                                    
						lay.style.display = "block";                                                                    
						if(! tree_Visible[lay.id]){                                                                    
							for(; idx < tree_LayerBox.length; idx++){                                                                    
								if(tree_LayerBox[idx].id.indexOf(lay.id) < 0){                                                                    
									idx--;                                                                    
									break;                                                                    
								}                                                                    
							}                                                                    
						}                                                                    
					}                                                                    
					else{                                                                    
						break;                                                                    
					}                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
	}                                                                    
	function tree_click_folder(lay_id, user_arg){                                                                    
		if((typeof tree_FolderAction) == "function"){                                                                    
			tree_highlight(lay_id);                                                                    
			tree_FolderAction(user_arg);                                                                    
		}                                                                    
		else{                                                                    
			tree_switch_folder(lay_id);                                                                    
		}                                                                    
		return false;                                                                    
	}                                                                    
	function tree_click_item(lay_id, user_arg){                                                                    
		if((typeof tree_ItemAction) == "function"){                                                                    
			tree_highlight(lay_id);                                                                    
			tree_ItemAction(user_arg);                                                                    
		}                                                                    
		return false;                                                                    
	}                                                                    
	function tree_switch_open(){                                                                    
		var idx;                                                                    
		for(idx in tree_Visible){
			tree_Visible[idx] = true; 
		}                                                                    
		for(idx in tree_line_img){                                                                    
			tree_line_img[idx] = tree_line_img_group[idx][tree_TRUE];                                                                    
		}                                                                    
                                                                    
		if(tree_isNN){                                                                    
			var lay_height = tree_BeginPoint;                                                                    
			for(idx in tree_LayerBox){                                                                    
				tree_LayerBox[idx].hidden = false;                                                                    
				tree_LayerBox[idx].top = lay_height;                                                                    
				lay_height += tree_LayerBox[idx].clip.height;                                                                    
				if(tree_line_img[tree_LayerBox[idx].id] != null){                                                                    
					tree_CrossImg[tree_LayerBox[idx].id].src = tree_line_img[tree_LayerBox[idx].id];                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
		if(tree_isIE){                                                                    
			for(idx in tree_LayerBox){                                                                    
				tree_LayerBox[idx].style.display = "block";                                                                    
				if(tree_line_img[tree_LayerBox[idx].id] != null){                                                                    
					tree_CrossImg[tree_LayerBox[idx].id].src = tree_line_img[tree_LayerBox[idx].id];                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
		if(tree_isN6){                                                                    
			for(idx in tree_LayerBox){                                                                    
				tree_LayerBox[idx].style.display = "block";                                                                    
				if(tree_line_img[tree_LayerBox[idx].id] != null){                                                                    
					tree_CrossImg[tree_LayerBox[idx].id].src = tree_line_img[tree_LayerBox[idx].id];                                                                    
				}                                                                    
			}                                                                    
		}                                                                    
	}                                                                    
	var tree_HomeAction = window[document.tree_event.home_action.value];                                                                    
	var tree_FolderAction = window[document.tree_event.folder_action.value];                                                                    
	var tree_ItemAction = window[document.tree_event.item_action.value];                                                                    
	tree_initialize();                                                                    