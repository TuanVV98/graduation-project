import React from "react";
import ShowDentist from "./ShowDentist";
import ShowService from "./ShowService";
import Advice from "./Advice";
import Header from "./Header";
import News from "./News"
import Partner from "./Partner";
export default function HomePage(){
    return(
        <div>
            <Header></Header>
            <ShowService></ShowService>
            <ShowDentist></ShowDentist>
            <Advice></Advice>
            <News></News>
            <Partner></Partner>
        </div>
    );
}