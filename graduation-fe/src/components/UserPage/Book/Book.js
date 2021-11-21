import React from 'react';
import ImgHeaderBook from './ImgHeaderBook';
import ShowService from '../HomePage/ShowService';
import FormBook from './FormBook';

export default function Book(){
    return(
        <div>
            <ImgHeaderBook></ImgHeaderBook>
            <ShowService></ShowService>
            <FormBook></FormBook>
        </div>
    )
    
}