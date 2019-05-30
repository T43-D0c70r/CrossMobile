// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.uikit.UIImagePickerController implementation

#import "crossmobile_ios_coregraphics_CGAffineTransform.h"
#import "crossmobile_ios_coregraphics_CGSize.h"
#import "crossmobile_ios_foundation_NSExtensionContext.h"
#import "crossmobile_ios_uikit_UIBarButtonItem.h"
#import "crossmobile_ios_uikit_UIEdgeInsets.h"
#import "crossmobile_ios_uikit_UIEvent.h"
#import "crossmobile_ios_uikit_UIImagePickerController.h"
#import "crossmobile_ios_uikit_UILayoutSupport.h"
#import "crossmobile_ios_uikit_UINavigationBar.h"
#import "crossmobile_ios_uikit_UINavigationController.h"
#import "crossmobile_ios_uikit_UINavigationControllerDelegate.h"
#import "crossmobile_ios_uikit_UINavigationItem.h"
#import "crossmobile_ios_uikit_UIResponder.h"
#import "crossmobile_ios_uikit_UISplitViewController.h"
#import "crossmobile_ios_uikit_UIStoryboard.h"
#import "crossmobile_ios_uikit_UIStoryboardSegue.h"
#import "crossmobile_ios_uikit_UIStoryboardUnwindSegueSource.h"
#import "crossmobile_ios_uikit_UITabBarController.h"
#import "crossmobile_ios_uikit_UITabBarItem.h"
#import "crossmobile_ios_uikit_UIToolbar.h"
#import "crossmobile_ios_uikit_UIView.h"
#import "crossmobile_ios_uikit_UIViewController.h"
#import "java_lang_Object.h"
#import "java_lang_String.h"
#import "java_util_List.h"
#import "java_util_Set.h"

@implementation crossmobile_ios_uikit_UIImagePickerController$Ext

// (UIViewController) @property(nonatomic) UIEdgeInsets additionalSafeAreaInsets;
- (void) setAdditionalSafeAreaInsets___crossmobile_ios_uikit_UIEdgeInsets:(crossmobile_ios_uikit_UIEdgeInsets*) additionalSafeAreaInsets 
{
    [super setAdditionalSafeAreaInsets:[additionalSafeAreaInsets getUIEdgeInsets]];
}

// (UIViewController) @property(nonatomic) UIEdgeInsets additionalSafeAreaInsets;
- (crossmobile_ios_uikit_UIEdgeInsets*) additionalSafeAreaInsets__
{
    return [[crossmobile_ios_uikit_UIEdgeInsets alloc] initWithUIEdgeInsets:[super additionalSafeAreaInsets]];
}

// (UIImagePickerController) @property(nonatomic) BOOL allowsEditing;
- (void) setAllowsEditing___boolean:(BOOL) allowsEditing 
{
    [super setAllowsEditing:allowsEditing];
}

// (UIImagePickerController) @property(nonatomic) BOOL allowsEditing;
- (BOOL) allowsEditing__
{
    return [super allowsEditing];
}

// (UIViewController) @property(nonatomic, assign) BOOL automaticallyAdjustsScrollViewInsets;
- (void) setAutomaticallyAdjustsScrollViewInsets___boolean:(BOOL) automaticallyAdjustsScrollViewInsets 
{
    [super setAutomaticallyAdjustsScrollViewInsets:automaticallyAdjustsScrollViewInsets];
}

// (UIViewController) @property(nonatomic, assign) BOOL automaticallyAdjustsScrollViewInsets;
- (BOOL) automaticallyAdjustsScrollViewInsets__
{
    return [super automaticallyAdjustsScrollViewInsets];
}

// (UIViewController) @property(nonatomic, readonly, strong) id<UILayoutSupport> bottomLayoutGuide;
- (id<UILayoutSupport>) bottomLayoutGuide__
{
    id<UILayoutSupport> re$ult = [super bottomLayoutGuide];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode;
- (void) setCameraCaptureMode___int:(int) cameraCaptureMode 
{
    [super setCameraCaptureMode:cameraCaptureMode];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode;
- (int) cameraCaptureMode__
{
    return [super cameraCaptureMode];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerCameraDevice cameraDevice;
- (void) setCameraDevice___int:(int) cameraDevice 
{
    [super setCameraDevice:cameraDevice];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerCameraDevice cameraDevice;
- (int) cameraDevice__
{
    return [super cameraDevice];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode;
- (void) setCameraFlashMode___int:(int) cameraFlashMode 
{
    [super setCameraFlashMode:cameraFlashMode];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode;
- (int) cameraFlashMode__
{
    return [super cameraFlashMode];
}

// (UIImagePickerController) @property(nonatomic, strong) __kindof UIView *cameraOverlayView;
- (void) setCameraOverlayView___crossmobile_ios_uikit_UIView:(UIView*) cameraOverlayView 
{
    [super setCameraOverlayView:(cameraOverlayView == JAVA_NULL ? nil : cameraOverlayView)];
}

// (UIImagePickerController) @property(nonatomic, strong) __kindof UIView *cameraOverlayView;
- (UIView*) cameraOverlayView__
{
    UIView* re$ult = [super cameraOverlayView];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIImagePickerController) @property(nonatomic) CGAffineTransform cameraViewTransform;
- (void) setCameraViewTransform___crossmobile_ios_coregraphics_CGAffineTransform:(crossmobile_ios_coregraphics_CGAffineTransform*) cameraViewTransform 
{
    [super setCameraViewTransform:[cameraViewTransform getCGAffineTransform]];
}

// (UIImagePickerController) @property(nonatomic) CGAffineTransform cameraViewTransform;
- (crossmobile_ios_coregraphics_CGAffineTransform*) cameraViewTransform__
{
    return [[crossmobile_ios_coregraphics_CGAffineTransform alloc] initWithCGAffineTransform:[super cameraViewTransform]];
}

// (UIViewController) @property(nonatomic, readonly) NSArray<__kindof UIViewController *> *childViewControllers;
- (NSArray*) childViewControllers__
{
    NSArray* re$ult = [super childViewControllers];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readwrite) CGSize contentSizeForViewInPopover;
- (void) setContentSizeForViewInPopover___crossmobile_ios_coregraphics_CGSize:(crossmobile_ios_coregraphics_CGSize*) contentSizeForViewInPopover 
{
    [super setContentSizeForViewInPopover:[contentSizeForViewInPopover getCGSize]];
}

// (UIViewController) @property(nonatomic, readwrite) CGSize contentSizeForViewInPopover;
- (crossmobile_ios_coregraphics_CGSize*) contentSizeForViewInPopover__
{
    return [[crossmobile_ios_coregraphics_CGSize alloc] initWithCGSize:[super contentSizeForViewInPopover]];
}

// (UIViewController) @property(nonatomic, assign) BOOL definesPresentationContext;
- (void) setDefinesPresentationContext___boolean:(BOOL) definesPresentationContext 
{
    [super setDefinesPresentationContext:definesPresentationContext];
}

// (UIViewController) @property(nonatomic, assign) BOOL definesPresentationContext;
- (BOOL) definesPresentationContext__
{
    return [super definesPresentationContext];
}

// (UINavigationController) @property(nonatomic, weak) id<UINavigationControllerDelegate> delegate;
- (void) setDelegate___crossmobile_ios_uikit_UINavigationControllerDelegate:(id<UINavigationControllerDelegate>) delegate 
{
    objc_setAssociatedObject(self, @selector(setDelegate:), delegate, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
    [super setDelegate:(delegate == JAVA_NULL ? nil : delegate)];
}

// (UINavigationController) @property(nonatomic, weak) id<UINavigationControllerDelegate> delegate;
- (id<UINavigationControllerDelegate>) delegate__
{
    id<UINavigationControllerDelegate> re$ult = [super delegate];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly) BOOL disablesAutomaticKeyboardDismissal;
- (BOOL) disablesAutomaticKeyboardDismissal__
{
    return [super disablesAutomaticKeyboardDismissal];
}

// (UIViewController) @property(nonatomic, assign) UIRectEdge edgesForExtendedLayout;
- (void) setEdgesForExtendedLayout___int:(int) edgesForExtendedLayout 
{
    [super setEdgesForExtendedLayout:edgesForExtendedLayout];
}

// (UIViewController) @property(nonatomic, assign) UIRectEdge edgesForExtendedLayout;
- (int) edgesForExtendedLayout__
{
    return [super edgesForExtendedLayout];
}

// (UIViewController) @property(nonatomic, getter=isEditing) BOOL editing;
- (void) setEditing___boolean:(BOOL) editing 
{
    [super setEditing:editing];
}

// (UIViewController) @property(nonatomic, getter=isEditing) BOOL editing;
- (BOOL) isEditing__
{
    return [super isEditing];
}

// (UIViewController) @property(nonatomic, readonly, strong) NSExtensionContext *extensionContext;
- (NSExtensionContext*) extensionContext__
{
    NSExtensionContext* re$ult = [super extensionContext];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic) BOOL hidesBottomBarWhenPushed;
- (void) setHidesBottomBarWhenPushed___boolean:(BOOL) hidesBottomBarWhenPushed 
{
    [super setHidesBottomBarWhenPushed:hidesBottomBarWhenPushed];
}

// (UIViewController) @property(nonatomic) BOOL hidesBottomBarWhenPushed;
- (BOOL) hidesBottomBarWhenPushed__
{
    return [super hidesBottomBarWhenPushed];
}

// (UIViewController) @property(nonatomic, readonly) UIInterfaceOrientation interfaceOrientation;
- (int) interfaceOrientation__
{
    return [super interfaceOrientation];
}

// (UIImagePickerController) @property(nonatomic, copy) NSArray<NSString *> *mediaTypes;
- (void) setMediaTypes___java_util_List:(NSArray*) mediaTypes 
{
    [super setMediaTypes:(mediaTypes == JAVA_NULL ? nil : mediaTypes)];
}

// (UIImagePickerController) @property(nonatomic, copy) NSArray<NSString *> *mediaTypes;
- (NSArray*) mediaTypes__
{
    NSArray* re$ult = [super mediaTypes];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readwrite, getter=isModalInPopover) BOOL modalInPopover;
- (void) setModalInPopover___boolean:(BOOL) modalInPopover 
{
    [super setModalInPopover:modalInPopover];
}

// (UIViewController) @property(nonatomic, readwrite, getter=isModalInPopover) BOOL modalInPopover;
- (BOOL) isModalInPopover__
{
    return [super isModalInPopover];
}

// (UIViewController) @property(nonatomic, assign) UIModalPresentationStyle modalPresentationStyle;
- (void) setModalPresentationStyle___int:(int) modalPresentationStyle 
{
    [super setModalPresentationStyle:modalPresentationStyle];
}

// (UIViewController) @property(nonatomic, assign) UIModalPresentationStyle modalPresentationStyle;
- (int) modalPresentationStyle__
{
    return [super modalPresentationStyle];
}

// (UIViewController) @property(nonatomic, assign) UIModalTransitionStyle modalTransitionStyle;
- (void) setModalTransitionStyle___int:(int) modalTransitionStyle 
{
    [super setModalTransitionStyle:modalTransitionStyle];
}

// (UIViewController) @property(nonatomic, assign) UIModalTransitionStyle modalTransitionStyle;
- (int) modalTransitionStyle__
{
    return [super modalTransitionStyle];
}

// (UINavigationController) @property(nonatomic, readonly) UINavigationBar *navigationBar;
- (UINavigationBar*) navigationBar__
{
    UINavigationBar* re$ult = [super navigationBar];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UINavigationController) @property(nonatomic, getter=isNavigationBarHidden) BOOL navigationBarHidden;
- (void) setNavigationBarHidden___boolean:(BOOL) navigationBarHidden 
{
    [super setNavigationBarHidden:navigationBarHidden];
}

// (UINavigationController) @property(nonatomic, getter=isNavigationBarHidden) BOOL navigationBarHidden;
- (BOOL) isNavigationBarHidden__
{
    return [super isNavigationBarHidden];
}

// (UIViewController) @property(nonatomic, readonly, strong) UINavigationController *navigationController;
- (UINavigationController*) navigationController__
{
    UINavigationController* re$ult = [super navigationController];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly, strong) UINavigationItem *navigationItem;
- (UINavigationItem*) navigationItem__
{
    UINavigationItem* re$ult = [super navigationItem];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly, copy) NSString *nibName;
- (NSString*) nibName__
{
    NSString* re$ult = [super nibName];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, weak, readonly) UIViewController *parentViewController;
- (UIViewController*) parentViewController__
{
    UIViewController* re$ult = [super parentViewController];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly) UIViewController *presentedViewController;
- (UIViewController*) presentedViewController__
{
    UIViewController* re$ult = [super presentedViewController];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly) UIViewController *presentingViewController;
- (UIViewController*) presentingViewController__
{
    UIViewController* re$ult = [super presentingViewController];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, assign) BOOL providesPresentationContextTransitionStyle;
- (void) setProvidesPresentationContextTransitionStyle___boolean:(BOOL) providesPresentationContextTransitionStyle 
{
    [super setProvidesPresentationContextTransitionStyle:providesPresentationContextTransitionStyle];
}

// (UIViewController) @property(nonatomic, assign) BOOL providesPresentationContextTransitionStyle;
- (BOOL) providesPresentationContextTransitionStyle__
{
    return [super providesPresentationContextTransitionStyle];
}

// (UIViewController) @property(nonatomic, copy) NSString *restorationIdentifier;
- (void) setRestorationIdentifier___java_lang_String:(NSString*) restorationIdentifier 
{
    [super setRestorationIdentifier:(restorationIdentifier == JAVA_NULL ? nil : restorationIdentifier)];
}

// (UIViewController) @property(nonatomic, copy) NSString *restorationIdentifier;
- (NSString*) restorationIdentifier__
{
    NSString* re$ult = [super restorationIdentifier];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly) BOOL shouldAutomaticallyForwardAppearanceMethods;
- (BOOL) shouldAutomaticallyForwardAppearanceMethods__
{
    return [super shouldAutomaticallyForwardAppearanceMethods];
}

// (UIImagePickerController) @property(nonatomic) BOOL showsCameraControls;
- (void) setShowsCameraControls___boolean:(BOOL) showsCameraControls 
{
    [super setShowsCameraControls:showsCameraControls];
}

// (UIImagePickerController) @property(nonatomic) BOOL showsCameraControls;
- (BOOL) showsCameraControls__
{
    return [super showsCameraControls];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerSourceType sourceType;
- (void) setSourceType___int:(int) sourceType 
{
    [super setSourceType:sourceType];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerSourceType sourceType;
- (int) sourceType__
{
    return [super sourceType];
}

// (UIViewController) @property(nonatomic, readonly, strong) UISplitViewController *splitViewController;
- (UISplitViewController*) splitViewController__
{
    UISplitViewController* re$ult = [super splitViewController];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly, strong) UIStoryboard *storyboard;
- (UIStoryboard*) storyboard__
{
    UIStoryboard* re$ult = [super storyboard];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly, strong) UITabBarController *tabBarController;
- (UITabBarController*) tabBarController__
{
    UITabBarController* re$ult = [super tabBarController];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, strong) UITabBarItem *tabBarItem;
- (void) setTabBarItem___crossmobile_ios_uikit_UITabBarItem:(UITabBarItem*) tabBarItem 
{
    [super setTabBarItem:(tabBarItem == JAVA_NULL ? nil : tabBarItem)];
}

// (UIViewController) @property(nonatomic, strong) UITabBarItem *tabBarItem;
- (UITabBarItem*) tabBarItem__
{
    UITabBarItem* re$ult = [super tabBarItem];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, copy) NSString *title;
- (void) setTitle___java_lang_String:(NSString*) title 
{
    [super setTitle:(title == JAVA_NULL ? nil : title)];
}

// (UIViewController) @property(nonatomic, copy) NSString *title;
- (NSString*) title__
{
    NSString* re$ult = [super title];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UINavigationController) @property(nonatomic, readonly) UIToolbar *toolbar;
- (UIToolbar*) toolbar__
{
    UIToolbar* re$ult = [super toolbar];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UINavigationController) @property(nonatomic, getter=isToolbarHidden) BOOL toolbarHidden;
- (void) setToolbarHidden___boolean:(BOOL) toolbarHidden 
{
    [super setToolbarHidden:toolbarHidden];
}

// (UINavigationController) @property(nonatomic, getter=isToolbarHidden) BOOL toolbarHidden;
- (BOOL) isToolbarHidden__
{
    return [super isToolbarHidden];
}

// (UIViewController) @property(nonatomic, strong) NSArray<__kindof UIBarButtonItem *> *toolbarItems;
- (void) setToolbarItems___java_util_List:(NSArray*) toolbarItems 
{
    [super setToolbarItems:(toolbarItems == JAVA_NULL ? nil : toolbarItems)];
}

// (UIViewController) @property(nonatomic, strong) NSArray<__kindof UIBarButtonItem *> *toolbarItems;
- (NSArray*) toolbarItems__
{
    NSArray* re$ult = [super toolbarItems];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly, strong) id<UILayoutSupport> topLayoutGuide;
- (id<UILayoutSupport>) topLayoutGuide__
{
    id<UILayoutSupport> re$ult = [super topLayoutGuide];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UINavigationController) @property(nonatomic, readonly, strong) UIViewController *topViewController;
- (UIViewController*) topViewController__
{
    UIViewController* re$ult = [super topViewController];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIImagePickerController) @property(nonatomic) NSTimeInterval videoMaximumDuration;
- (void) setVideoMaximumDuration___double:(double) videoMaximumDuration 
{
    [super setVideoMaximumDuration:videoMaximumDuration];
}

// (UIImagePickerController) @property(nonatomic) NSTimeInterval videoMaximumDuration;
- (double) videoMaximumDuration__
{
    return [super videoMaximumDuration];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerQualityType videoQuality;
- (void) setVideoQuality___int:(int) videoQuality 
{
    [super setVideoQuality:videoQuality];
}

// (UIImagePickerController) @property(nonatomic) UIImagePickerControllerQualityType videoQuality;
- (int) videoQuality__
{
    return [super videoQuality];
}

// (UIViewController) @property(nonatomic, strong) UIView *view;
- (void) setView___crossmobile_ios_uikit_UIView:(UIView*) view 
{
    [super setView:(view == JAVA_NULL ? nil : view)];
}

// (UIViewController) @property(nonatomic, strong) UIView *view;
- (UIView*) view__
{
    UIView* re$ult = [super view];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UINavigationController) @property(nonatomic, copy) NSArray<__kindof UIViewController *> *viewControllers;
- (void) setViewControllers___java_util_List:(NSArray*) viewControllers 
{
    [super setViewControllers:(viewControllers == JAVA_NULL ? nil : viewControllers)];
}

// (UINavigationController) @property(nonatomic, copy) NSArray<__kindof UIViewController *> *viewControllers;
- (NSArray*) viewControllers__
{
    NSArray* re$ult = [super viewControllers];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, readonly, getter=isViewLoaded) BOOL viewLoaded;
- (BOOL) isViewLoaded__
{
    return [super isViewLoaded];
}

// (UINavigationController) @property(nonatomic, readonly, strong) UIViewController *visibleViewController;
- (UIViewController*) visibleViewController__
{
    UIViewController* re$ult = [super visibleViewController];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) @property(nonatomic, assign) BOOL wantsFullScreenLayout;
- (void) setWantsFullScreenLayout___boolean:(BOOL) wantsFullScreenLayout 
{
    [super setWantsFullScreenLayout:wantsFullScreenLayout];
}

// (UIViewController) @property(nonatomic, assign) BOOL wantsFullScreenLayout;
- (BOOL) wantsFullScreenLayout__
{
    return [super wantsFullScreenLayout];
}

// (UIViewController) - (void)addChildViewController:(UIViewController *)childController;
- (void) addChildViewController___crossmobile_ios_uikit_UIViewController:(UIViewController*) childController 
{
    [super addChildViewController:(childController == JAVA_NULL ? nil : childController)];
}

// (UIViewController) - (NSArray<UIViewController *> *)allowedChildViewControllersForUnwindingFromSource:(UIStoryboardUnwindSegueSource *)source;
- (NSArray*) allowedChildViewControllersForUnwindingFromSource___crossmobile_ios_uikit_UIStoryboardUnwindSegueSource:(UIStoryboardUnwindSegueSource*) source 
{
    NSArray* re$ult = [super allowedChildViewControllersForUnwindingFromSource:(source == JAVA_NULL ? nil : source)];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIResponder) - (BOOL)becomeFirstResponder;
- (BOOL) becomeFirstResponder__
{
    return [super becomeFirstResponder];
}

// (UIViewController) - (void)beginAppearanceTransition:(BOOL)isAppearing animated:(BOOL)animated;
- (void) beginAppearanceTransition___boolean_boolean:(BOOL) isAppearing :(BOOL) animated 
{
    [super beginAppearanceTransition:isAppearing animated:animated];
}

// (UIViewController) - (UIViewController *)childViewControllerContainingSegueSource:(UIStoryboardUnwindSegueSource *)source;
- (UIViewController*) childViewControllerContainingSegueSource___crossmobile_ios_uikit_UIStoryboardUnwindSegueSource:(UIStoryboardUnwindSegueSource*) source 
{
    UIViewController* re$ult = [super childViewControllerContainingSegueSource:(source == JAVA_NULL ? nil : source)];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) - (void)didReceiveMemoryWarning;
- (void) didReceiveMemoryWarning__
{
    [super didReceiveMemoryWarning];
}

// (UIViewController) - (void)didRotateFromInterfaceOrientation:(UIInterfaceOrientation)fromInterfaceOrientation;
- (void) didRotateFromInterfaceOrientation___int:(int) fromInterfaceOrientation 
{
    [super didRotateFromInterfaceOrientation:fromInterfaceOrientation];
}

// (UIViewController) - (void)dismissModalViewControllerAnimated:(BOOL)animated;
- (void) dismissModalViewControllerAnimated___boolean:(BOOL) animated 
{
    [super dismissModalViewControllerAnimated:animated];
}

// (UIViewController) - (UIBarButtonItem *)editButtonItem;
- (UIBarButtonItem*) editButtonItem__
{
    UIBarButtonItem* re$ult = [super editButtonItem];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) - (void)endAppearanceTransition;
- (void) endAppearanceTransition__
{
    [super endAppearanceTransition];
}

// (UIResponder) - (BOOL)isFirstResponder;
- (BOOL) isFirstResponder__
{
    return [super isFirstResponder];
}

// (UIViewController) - (void)loadView;
- (void) loadView__
{
    [super loadView];
}

// (UIResponder) - (UIResponder *)nextResponder;
- (UIResponder*) nextResponder__
{
    UIResponder* re$ult = [super nextResponder];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) - (void)performSegueWithIdentifier:(NSString *)identifier sender:(id)sender;
- (void) performSegueWithIdentifier___java_lang_String_java_lang_Object:(NSString*) identifier :(id) sender 
{
    [super performSegueWithIdentifier:(identifier == JAVA_NULL ? nil : identifier) sender:(sender == JAVA_NULL ? nil : sender)];
}

// (UINavigationController) - (NSArray<__kindof UIViewController *> *)popToRootViewControllerAnimated:(BOOL)animated;
- (NSArray*) popToRootViewControllerAnimated___boolean:(BOOL) animated 
{
    NSArray* re$ult = [super popToRootViewControllerAnimated:animated];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UINavigationController) - (NSArray<__kindof UIViewController *> *)popToViewController:(UIViewController *)viewController animated:(BOOL)animated;
- (NSArray*) popToViewController___crossmobile_ios_uikit_UIViewController_boolean:(UIViewController*) viewController :(BOOL) animated 
{
    NSArray* re$ult = [super popToViewController:(viewController == JAVA_NULL ? nil : viewController) animated:animated];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UINavigationController) - (UIViewController *)popViewControllerAnimated:(BOOL)animated;
- (UIViewController*) popViewControllerAnimated___boolean:(BOOL) animated 
{
    UIViewController* re$ult = [super popViewControllerAnimated:animated];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) - (UIInterfaceOrientation)preferredInterfaceOrientationForPresentation;
- (int) preferredInterfaceOrientationForPresentation__
{
    return [super preferredInterfaceOrientationForPresentation];
}

// (UIViewController) - (BOOL)prefersStatusBarHidden;
- (BOOL) prefersStatusBarHidden__
{
    return [super prefersStatusBarHidden];
}

// (UIViewController) - (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender;
- (void) prepareForSegue___crossmobile_ios_uikit_UIStoryboardSegue_java_lang_Object:(UIStoryboardSegue*) segue :(id) sender 
{
    [super prepareForSegue:(segue == JAVA_NULL ? nil : segue) sender:(sender == JAVA_NULL ? nil : sender)];
}

// (UIViewController) - (void)presentModalViewController:(UIViewController *)modalViewController animated:(BOOL)animated;
- (void) presentModalViewController___crossmobile_ios_uikit_UIViewController_boolean:(UIViewController*) modalViewController :(BOOL) animated 
{
    [super presentModalViewController:(modalViewController == JAVA_NULL ? nil : modalViewController) animated:animated];
}

// (UINavigationController) - (void)pushViewController:(UIViewController *)viewController animated:(BOOL)animated;
- (void) pushViewController___crossmobile_ios_uikit_UIViewController_boolean:(UIViewController*) viewController :(BOOL) animated 
{
    [super pushViewController:(viewController == JAVA_NULL ? nil : viewController) animated:animated];
}

// (UIViewController) - (void)removeFromParentViewController;
- (void) removeFromParentViewController__
{
    [super removeFromParentViewController];
}

// (UIResponder) - (BOOL)resignFirstResponder;
- (BOOL) resignFirstResponder__
{
    return [super resignFirstResponder];
}

// (UIViewController) - (UIView *)rotatingFooterView;
- (UIView*) rotatingFooterView__
{
    UIView* re$ult = [super rotatingFooterView];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) - (UIView *)rotatingHeaderView;
- (UIView*) rotatingHeaderView__
{
    UIView* re$ult = [super rotatingHeaderView];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) - (void)setEditing:(BOOL)editing animated:(BOOL)animated;
- (void) setEditing___boolean_boolean:(BOOL) editing :(BOOL) animated 
{
    [super setEditing:editing animated:animated];
}

// (UINavigationController) - (void)setNavigationBarHidden:(BOOL)hidden animated:(BOOL)animated;
- (void) setNavigationBarHidden___boolean_boolean:(BOOL) hidden :(BOOL) animated 
{
    [super setNavigationBarHidden:hidden animated:animated];
}

// (UINavigationController) - (void)setToolbarHidden:(BOOL)hidden animated:(BOOL)animated;
- (void) setToolbarHidden___boolean_boolean:(BOOL) hidden :(BOOL) animated 
{
    [super setToolbarHidden:hidden animated:animated];
}

// (UIViewController) - (void)setToolbarItems:(NSArray<UIBarButtonItem *> *)toolbarItems animated:(BOOL)animated;
- (void) setToolbarItems___java_util_List_boolean:(NSArray*) toolbarItems :(BOOL) animated 
{
    [super setToolbarItems:(toolbarItems == JAVA_NULL ? nil : toolbarItems) animated:animated];
}

// (NSObject) - (void)setValue:(id)value forKey:(NSString *)key;
- (void) setValue___java_lang_Object_java_lang_String:(id) value :(NSString*) key 
{
    [super setValue:(value == JAVA_NULL ? nil : value) forKey:(key == JAVA_NULL ? nil : key)];
}

// (UINavigationController) - (void)setViewControllers:(NSArray<UIViewController *> *)viewControllers animated:(BOOL)animated;
- (void) setViewControllers___java_util_List_boolean:(NSArray*) viewControllers :(BOOL) animated 
{
    [super setViewControllers:(viewControllers == JAVA_NULL ? nil : viewControllers) animated:animated];
}

// (UIViewController) - (BOOL)shouldAutorotate;
- (BOOL) shouldAutorotate__
{
    return [super shouldAutorotate];
}

// (UIViewController) - (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation;
- (BOOL) shouldAutorotateToInterfaceOrientation___int:(int) toInterfaceOrientation 
{
    return [super shouldAutorotateToInterfaceOrientation:toInterfaceOrientation];
}

// (UIViewController) - (BOOL)shouldPerformSegueWithIdentifier:(NSString *)identifier sender:(id)sender;
- (BOOL) shouldPerformSegueWithIdentifier___java_lang_String_java_lang_Object:(NSString*) identifier :(id) sender 
{
    return [super shouldPerformSegueWithIdentifier:(identifier == JAVA_NULL ? nil : identifier) sender:(sender == JAVA_NULL ? nil : sender)];
}

// (UIViewController) - (void)showDetailViewController:(UIViewController *)vc sender:(id)sender;
- (void) showDetailViewController___crossmobile_ios_uikit_UIViewController_java_lang_Object:(UIViewController*) vc :(id) sender 
{
    [super showDetailViewController:(vc == JAVA_NULL ? nil : vc) sender:(sender == JAVA_NULL ? nil : sender)];
}

// (UIViewController) - (void)showViewController:(UIViewController *)vc sender:(id)sender;
- (void) showViewController___crossmobile_ios_uikit_UIViewController_java_lang_Object:(UIViewController*) vc :(id) sender 
{
    [super showViewController:(vc == JAVA_NULL ? nil : vc) sender:(sender == JAVA_NULL ? nil : sender)];
}

// (UIImagePickerController) - (BOOL)startVideoCapture;
- (BOOL) startVideoCapture__
{
    return [super startVideoCapture];
}

// (UIImagePickerController) - (void)stopVideoCapture;
- (void) stopVideoCapture__
{
    [super stopVideoCapture];
}

// (UIViewController) - (UIInterfaceOrientationMask)supportedInterfaceOrientations;
- (int) supportedInterfaceOrientations__
{
    return [super supportedInterfaceOrientations];
}

// (UIImagePickerController) - (void)takePicture;
- (void) takePicture__
{
    [super takePicture];
}

// (UIResponder) - (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event;
- (void) touchesBegan___java_util_Set_crossmobile_ios_uikit_UIEvent:(NSSet*) touches :(UIEvent*) event 
{
    [super touchesBegan:(touches == JAVA_NULL ? nil : touches) withEvent:(event == JAVA_NULL ? nil : event)];
}

// (UIResponder) - (void)touchesCancelled:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event;
- (void) touchesCancelled___java_util_Set_crossmobile_ios_uikit_UIEvent:(NSSet*) touches :(UIEvent*) event 
{
    [super touchesCancelled:(touches == JAVA_NULL ? nil : touches) withEvent:(event == JAVA_NULL ? nil : event)];
}

// (UIResponder) - (void)touchesEnded:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event;
- (void) touchesEnded___java_util_Set_crossmobile_ios_uikit_UIEvent:(NSSet*) touches :(UIEvent*) event 
{
    [super touchesEnded:(touches == JAVA_NULL ? nil : touches) withEvent:(event == JAVA_NULL ? nil : event)];
}

// (UIResponder) - (void)touchesMoved:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event;
- (void) touchesMoved___java_util_Set_crossmobile_ios_uikit_UIEvent:(NSSet*) touches :(UIEvent*) event 
{
    [super touchesMoved:(touches == JAVA_NULL ? nil : touches) withEvent:(event == JAVA_NULL ? nil : event)];
}

// (NSObject) - (id)valueForKey:(NSString *)key;
- (id) valueForKey___java_lang_String:(NSString*) key 
{
    id re$ult = [super valueForKey:(key == JAVA_NULL ? nil : key)];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIViewController) - (void)viewDidAppear:(BOOL)animated;
- (void) viewDidAppear___boolean:(BOOL) animated 
{
    [super viewDidAppear:animated];
}

// (UIViewController) - (void)viewDidDisappear:(BOOL)animated;
- (void) viewDidDisappear___boolean:(BOOL) animated 
{
    [super viewDidDisappear:animated];
}

// (UIViewController) - (void)viewDidLayoutSubviews;
- (void) viewDidLayoutSubviews__
{
    [super viewDidLayoutSubviews];
}

// (UIViewController) - (void)viewDidLoad;
- (void) viewDidLoad__
{
    [super viewDidLoad];
}

// (UIViewController) - (void)viewDidUnload;
- (void) viewDidUnload__
{
    [super viewDidUnload];
}

// (UIViewController) - (void)viewSafeAreaInsetsDidChange;
- (void) viewSafeAreaInsetsDidChange__
{
    [super viewSafeAreaInsetsDidChange];
}

// (UIViewController) - (void)viewWillAppear:(BOOL)animated;
- (void) viewWillAppear___boolean:(BOOL) animated 
{
    [super viewWillAppear:animated];
}

// (UIViewController) - (void)viewWillDisappear:(BOOL)animated;
- (void) viewWillDisappear___boolean:(BOOL) animated 
{
    [super viewWillDisappear:animated];
}

// (UIViewController) - (void)viewWillLayoutSubviews;
- (void) viewWillLayoutSubviews__
{
    [super viewWillLayoutSubviews];
}

// (UIViewController) - (void)willAnimateRotationToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation duration:(NSTimeInterval)duration;
- (void) willAnimateRotationToInterfaceOrientation___int_double:(int) toInterfaceOrientation :(double) duration 
{
    [super willAnimateRotationToInterfaceOrientation:toInterfaceOrientation duration:duration];
}

// (UIViewController) - (void)willRotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation duration:(NSTimeInterval)duration;
- (void) willRotateToInterfaceOrientation___int_double:(int) toInterfaceOrientation :(double) duration 
{
    [super willRotateToInterfaceOrientation:toInterfaceOrientation duration:duration];
}

@end

@implementation UIImagePickerController (cm_crossmobile_ios_uikit_UIImagePickerController)

// direct binding of: + (NSArray<NSNumber *> *)availableCaptureModesForCameraDevice:(UIImagePickerControllerCameraDevice)cameraDevice;
+ (NSArray*) availableCaptureModesForCameraDevice___int:(int) cameraDevice 
{
    NSArray* re$ult = [UIImagePickerController availableCaptureModesForCameraDevice:cameraDevice];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: + (NSArray<NSString *> *)availableMediaTypesForSourceType:(UIImagePickerControllerSourceType)sourceType;
+ (NSArray*) availableMediaTypesForSourceType___int:(int) sourceType 
{
    NSArray* re$ult = [UIImagePickerController availableMediaTypesForSourceType:sourceType];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: + (BOOL)isCameraDeviceAvailable:(UIImagePickerControllerCameraDevice)cameraDevice;
+ (BOOL) isCameraDeviceAvailable___int:(int) cameraDevice 
{
    return [UIImagePickerController isCameraDeviceAvailable:cameraDevice];
}

// direct binding of: + (BOOL)isFlashAvailableForCameraDevice:(UIImagePickerControllerCameraDevice)cameraDevice;
+ (BOOL) isFlashAvailableForCameraDevice___int:(int) cameraDevice 
{
    return [UIImagePickerController isFlashAvailableForCameraDevice:cameraDevice];
}

// direct binding of: + (BOOL)isSourceTypeAvailable:(UIImagePickerControllerSourceType)sourceType;
+ (BOOL) isSourceTypeAvailable___int:(int) sourceType 
{
    return [UIImagePickerController isSourceTypeAvailable:sourceType];
}

// direct binding of: -(instancetype) init;
- (instancetype) __init_crossmobile_ios_uikit_UIImagePickerController__
{
    return [self init];
}

// direct binding of: @property(nonatomic) BOOL allowsEditing;
- (void) setAllowsEditing___boolean:(BOOL) allowsEditing 
{
    [self setAllowsEditing:allowsEditing];
}

// direct binding of: @property(nonatomic) BOOL allowsEditing;
- (BOOL) allowsEditing__
{
    return [self allowsEditing];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode;
- (void) setCameraCaptureMode___int:(int) cameraCaptureMode 
{
    [self setCameraCaptureMode:cameraCaptureMode];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode;
- (int) cameraCaptureMode__
{
    return [self cameraCaptureMode];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerCameraDevice cameraDevice;
- (void) setCameraDevice___int:(int) cameraDevice 
{
    [self setCameraDevice:cameraDevice];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerCameraDevice cameraDevice;
- (int) cameraDevice__
{
    return [self cameraDevice];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode;
- (void) setCameraFlashMode___int:(int) cameraFlashMode 
{
    [self setCameraFlashMode:cameraFlashMode];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode;
- (int) cameraFlashMode__
{
    return [self cameraFlashMode];
}

// direct binding of: @property(nonatomic, strong) __kindof UIView *cameraOverlayView;
- (void) setCameraOverlayView___crossmobile_ios_uikit_UIView:(UIView*) cameraOverlayView 
{
    [self setCameraOverlayView:(cameraOverlayView == JAVA_NULL ? nil : cameraOverlayView)];
}

// direct binding of: @property(nonatomic, strong) __kindof UIView *cameraOverlayView;
- (UIView*) cameraOverlayView__
{
    UIView* re$ult = [self cameraOverlayView];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: @property(nonatomic) CGAffineTransform cameraViewTransform;
- (void) setCameraViewTransform___crossmobile_ios_coregraphics_CGAffineTransform:(crossmobile_ios_coregraphics_CGAffineTransform*) cameraViewTransform 
{
    [self setCameraViewTransform:[cameraViewTransform getCGAffineTransform]];
}

// direct binding of: @property(nonatomic) CGAffineTransform cameraViewTransform;
- (crossmobile_ios_coregraphics_CGAffineTransform*) cameraViewTransform__
{
    return [[crossmobile_ios_coregraphics_CGAffineTransform alloc] initWithCGAffineTransform:[self cameraViewTransform]];
}

// direct binding of: @property(nonatomic, copy) NSArray<NSString *> *mediaTypes;
- (void) setMediaTypes___java_util_List:(NSArray*) mediaTypes 
{
    [self setMediaTypes:(mediaTypes == JAVA_NULL ? nil : mediaTypes)];
}

// direct binding of: @property(nonatomic, copy) NSArray<NSString *> *mediaTypes;
- (NSArray*) mediaTypes__
{
    NSArray* re$ult = [self mediaTypes];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: @property(nonatomic) BOOL showsCameraControls;
- (void) setShowsCameraControls___boolean:(BOOL) showsCameraControls 
{
    [self setShowsCameraControls:showsCameraControls];
}

// direct binding of: @property(nonatomic) BOOL showsCameraControls;
- (BOOL) showsCameraControls__
{
    return [self showsCameraControls];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerSourceType sourceType;
- (void) setSourceType___int:(int) sourceType 
{
    [self setSourceType:sourceType];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerSourceType sourceType;
- (int) sourceType__
{
    return [self sourceType];
}

// direct binding of: @property(nonatomic) NSTimeInterval videoMaximumDuration;
- (void) setVideoMaximumDuration___double:(double) videoMaximumDuration 
{
    [self setVideoMaximumDuration:videoMaximumDuration];
}

// direct binding of: @property(nonatomic) NSTimeInterval videoMaximumDuration;
- (double) videoMaximumDuration__
{
    return [self videoMaximumDuration];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerQualityType videoQuality;
- (void) setVideoQuality___int:(int) videoQuality 
{
    [self setVideoQuality:videoQuality];
}

// direct binding of: @property(nonatomic) UIImagePickerControllerQualityType videoQuality;
- (int) videoQuality__
{
    return [self videoQuality];
}

// direct binding of: - (BOOL)startVideoCapture;
- (BOOL) startVideoCapture__
{
    return [self startVideoCapture];
}

// direct binding of: - (void)stopVideoCapture;
- (void) stopVideoCapture__
{
    [self stopVideoCapture];
}

// direct binding of: - (void)takePicture;
- (void) takePicture__
{
    [self takePicture];
}

@end