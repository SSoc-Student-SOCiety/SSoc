import React, { useState } from 'react';
import { View, TextInput } from 'react-native';
import * as Color from '../Colors/colors';
export const SingleLineInput = (props) => {
  const [focused, setFocused] = useState(false);

  return (
    <View
      style={{
        alignSelf: 'stretch',
        paddingHorizontal: 12,
        paddingVertical: 8,
        borderRadius: 4,
        borderWidth: 1,
        borderColor: focused ? Color.BLUE : Color.WHITE,
      }}
    >
      <TextInput
        autoCorrect={false}
        value={props.value}
        onChangeText={props.onChangeText}
        placeholder={props.placeholder}
        style={[props.style, { fontSize: props.fontSize ?? 20 }]}
        onFocus={() => {
          setFocused(true);
        }}
        onBlur={() => {
          setFocused(false);
        }}
        editable={props.editable}
        secureTextEntry={props.secureTextEntry}
        onSubmitEditing={props.onSubmitEditing}
      />
    </View>
  );
};
