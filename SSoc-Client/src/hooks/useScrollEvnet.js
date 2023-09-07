import React, { useRef } from "react";
import { Animated } from "react-native";

export const useScrollEvent = () => {
  const scrollStartRef = useRef(0); // Initialize with an appropriate value
  const headerAnim = useRef(new Animated.Value(0)).current;

  const onScrollEndDrag = (e) => {
    const y = e.nativeEvent.contentOffset.y;
    scrollStartRef.current = y;
  };

  const onScrollBeginDrag = (e) => {
    const y = e.nativeEvent.contentOffset.y;
    scrollStartRef.current = y;
  };

  const onScroll = (e) => {
    const y = e.nativeEvent.contentOffset.y;
    const dy = y - scrollStartRef.current;
    scrollStartRef.current = y; // Update scrollStartRef

    headerAnim.setValue(y);
    console.log(headerAnim);
  };

  return { onScrollEndDrag, onScrollBeginDrag, onScroll, headerAnim };
};
