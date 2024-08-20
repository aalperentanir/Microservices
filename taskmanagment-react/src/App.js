import { ThemeProvider } from "@mui/material";
import { darkTheme } from "./theme/darkTheme";
import Navbar from "./page/navbar/navbar";
import Sidebar from "./page/sidebaar/sidebar";
import Home from "./page/home/home";


function App() {
  return (
    <ThemeProvider theme={darkTheme}>

     <Navbar/>
     <Home/>

    </ThemeProvider>
  );
}

export default App;
