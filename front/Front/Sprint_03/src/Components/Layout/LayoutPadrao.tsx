import { Outlet } from "react-router-dom";
import Header from "../Header/Header";

const LayoutPadrao =() =>{
    return(
        <>
            <Header></Header>
            <main>
                <Outlet />
            </main>
        </>        
    )
}
 export default LayoutPadrao;