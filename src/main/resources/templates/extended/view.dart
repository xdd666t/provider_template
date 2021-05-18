import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'provider.dart';
import 'state.dart';

// ignore: must_be_immutable
class $namePage extends StatelessWidget {
  late $nameProvider mProvider;
  late $nameState mState;

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (BuildContext context) => $nameProvider(),
      child: Selector<$nameProvider, $nameProvider>(
        shouldRebuild: (previous, next) => false,
        selector: (context, provider) => provider,
        builder: (context, provider, child) {
          mProvider = provider;
          mState = provider.state;
          return _buildPage(context);
        },
      ),
    );
  }

  Widget _buildPage(BuildContext context) {
    return Container();
  }
}