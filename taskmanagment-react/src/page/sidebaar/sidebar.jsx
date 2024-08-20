import { Avatar, Button } from "@mui/material";
import React, { useState } from "react";
import "./sidebar.css";

const menu = [
  { name: "Home", value: "HOME", role: ["ADMIN", "USER"] },
  { name: "Done", value: "DONE", role: ["ADMIN", "USER"] },
  { name: "Assigned", value: "ASSIGNED", role: ["ADMIN"] },
  { name: "Not Assigned", value: "PENDING", role: ["ADMIN"] },
  { name: "Create New Task", value: "", role: ["ADMIN"] },
  { name: "Notification", value: "NOTIFICATION", role: ["USER"] },
];

const role = "ADMIN";
const Sidebar = () => {
  const [activeMenu, setActiveMenu] = useState("Home");

  const handleMenuChange = (item) => {
    setActiveMenu(item.name);
  };

  const handleLogout = () =>{
    console.log("Logout success")
  }
  return (
    <div className=" card min-h-[85vh] flex flex-col justify-center fixed w-[20vw]">
      <div className="space-y-5 h-full">
        <div className="flex justify-center">
          <Avatar
            sx={{ width: "8rem", height: "8rem" }}
            className="border-2 border-[#0a27ab]"
          />
        </div>

        {menu
          .filter((item) => item.role.includes(role))
          .map((item) => (
            <p
              onClick={() => handleMenuChange(item)}
              className={`py-3 px-5 rounded-full text-center cursor-pointer ${
                activeMenu === item.name ? "activeMenuItem" : "menuItem"
              }`}
            >
              {item.name}
            </p>
          ))}

        <Button onClick={handleLogout} sx={{padding:".7rem",borderRadius:"2rem"}} fullWidth className="logoutButton">Logout</Button>
      </div>
    </div>
  );
};

export default Sidebar;
