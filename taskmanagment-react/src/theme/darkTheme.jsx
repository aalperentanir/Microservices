import { createTheme } from "@mui/material";

export const darkTheme = createTheme({
    palette:{
        mode:"dark",
        background:{
            default: "#000000"
        },
        text:{
            primary:"#fff"
        },
        primary:{
            main:"rgba(68, 62, 182, 1)"
        }
    }
})