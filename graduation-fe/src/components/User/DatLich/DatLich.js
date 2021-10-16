import React from 'react';
import ShowDichVu from '../HomePage/ShowDichVu';
import FormDatLich from './FormDatLich';
import ImgHeaderDatLich from './ImgHeaderDatLich';

export default function DatLich(){
    return(
        <div>
            <ImgHeaderDatLich></ImgHeaderDatLich>
            <ShowDichVu></ShowDichVu>
            <FormDatLich></FormDatLich>
        </div>
    )
    
}