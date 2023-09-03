import React from "react";
import { Button } from "../Basic/Button";
import { Icon } from "../Icons/Icons";

export const HeaderIcon = (props) => {
  return (
    <Button onPress={props.onPress}>
      <Icon name={props.iconName} size={28} color="black" />
    </Button>
  );
};
