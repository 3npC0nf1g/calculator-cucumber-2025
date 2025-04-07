import { Tabs } from 'expo-router';
import React from 'react';
import {Platform, View,StyleSheet} from 'react-native';

import { IconSymbol } from '@/components/ui/IconSymbol';
import TabBarBackground from '@/components/ui/TabBarBackground';
import { Colors } from '@/constants/Colors';
import { useColorScheme } from '@/hooks/useColorScheme';

export default function TabLayout() {
  const colorScheme = useColorScheme();

  return (
    <Tabs
      screenOptions={({ route }) => ({
        tabBarActiveTintColor: Colors[colorScheme ?? 'light'].tint,
        headerShown: false,
        tabBarBackground: TabBarBackground,
        tabBarStyle: Platform.select({
          ios: {
            // Use a transparent background on iOS to show the blur effect
            position: 'absolute',
              display: route.name === 'index' ? 'none' : 'flex',
          },
          default: {
              display: route.name === 'index' ? 'none' : 'flex',
          },
        }),
      })}
    >
      <Tabs.Screen
        name="index"
        options={{
          title: 'Home',
            tabBarShowLabel:false,
            headerShown:false,

          tabBarIcon: ({ color }) => <IconSymbol size={28} name="house.fill" color={color} />,
        }}
      />

    </Tabs>
  );
}

