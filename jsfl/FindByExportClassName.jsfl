var searchName = prompt("请输入要查找的导出类");
if (searchName)
{
	var doc = fl.getDocumentDOM();
	var lib = doc.library;
	var find = false;
	for each(var libItem in lib.items)
	{
		var className = libItem.linkageClassName;
		if (className && className.toLowerCase() == searchName.toLowerCase())
		{
			lib.selectItem(libItem.name);
			expandItem(lib, libItem.name);
			lib.editItem(libItem.name);
			find = true;
			break;
		}
	}
	if (!find)
	{
		alert("要查找的导出类不存在");
	}
}

/**
 * expand item recursively
 */
function expandItem(lib, itemName)
{
	var parentName = getParentFolderName(itemName);
	if (null != parentName && parentName.length > 0)
	{
		lib.expandFolder(true, false, parentName);
		expandItem(lib, parentName);
	}
}

/**
 * get the parent folder name of an item
 */
function getParentFolderName(itemName)
{
	var slashIndex = itemName.lastIndexOf("/");
	if (slashIndex >= 0)
	{
		return itemName.substring(0, slashIndex);
	}
	return "";
}