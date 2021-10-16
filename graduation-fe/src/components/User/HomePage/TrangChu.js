import React from "react";
import ShowBacSi from "./ShowBacSi";
import ShowDichVu from "./ShowDichVu";
import LoiKhuyen from "./LoiKhuyen";
import Header from "./Header";
import TinTuc from "./TinTuc"
import DoiTac from "./DoiTac";
export default function TrangChu(){
    return(
        <div>
            <Header></Header>
            <ShowDichVu></ShowDichVu>
            <ShowBacSi></ShowBacSi>
            <LoiKhuyen></LoiKhuyen>
            <TinTuc></TinTuc>
            <DoiTac></DoiTac>
        </div>
    );
}