import React from "react";
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip, Legend } from 'recharts';
import styled from "styled-components";

function Dashboard() {

  const data = [
    {name: 'Page A', uv: 400, pv: 2400, amt: 2400},
    {name: 'Page B', uv: 400, pv: 2400, amt: 2400},
    {name: 'Page C', uv: 500, pv: 2800, amt: 2400},
    {name: 'Page D', uv: 600, pv: 3000, amt: 2400},
    {name: 'Page E', uv: 800, pv: 4500, amt: 2400}
  ]

  return (
    <>
      <h1>Recharts</h1>
      <LineChart width={730} height={250} data={data}
        margin={{ top: 5, right: 30, left: 20, bottom: 5 }}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="name" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="pv" stroke="#8884d8" />
        <Line type="monotone" dataKey="uv" stroke="#82ca9d" />
      </LineChart>
    </>
  );
}

export default Dashboard;
